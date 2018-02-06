package org.jeneva.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import org.jeneva.Dtobase;
import org.jeneva.IJsonParser;
import org.jeneva.IJenevaContext;
import org.jeneva.ListAndSet;

/**
 * Represents JSON parser utility
 */
public class JsonParser implements IJsonParser {

	private final IJenevaContext context;

	/**
	 * Initializes new instance of the JsonParser class
	 */
	public JsonParser(IJenevaContext context) {
		this.context = context;
	}

	/**
	 * Parses JSON data into domain object
	 * @param data JSON data
	 * @param type domain object type
	 * @return parsed domain/DTO object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Dtobase> T parse(JsonNode data, Class<?> type)
	{
		try {
			if(null == data || data.isNull()) {
				return null;
			}

			Dtobase dto = (Dtobase)type.newInstance();
			PropertyMeta[] properties = this.context.getPropertyDefs(type);
			for(int i = 0; i < properties.length; i++)
			{
				PropertyMeta propertyDef = properties[i];
				if(!propertyDef.isValid()) {
					continue;
				}

				JsonNode propertyValue = data.get(propertyDef.getName());
				if(null != propertyValue) {
					try {
						if(PropertyMeta.ClassType.Value == propertyDef.getPropertyClassType()) {
							dto.addAssignedField(propertyDef.getName());
							propertyDef.write(dto, this.parseValue(propertyValue, propertyDef));
						}
						else if(PropertyMeta.ClassType.Class == propertyDef.getPropertyClassType() || PropertyMeta.ClassType.CustomType == propertyDef.getPropertyClassType()) {
							dto.addAssignedField(propertyDef.getName());
							propertyDef.write(dto,
								this.parse(propertyValue, propertyDef.getPropertyClass()));
						}
						else if(PropertyMeta.ClassType.Collection == propertyDef.getPropertyClassType() || PropertyMeta.ClassType.CustomTypeCollection == propertyDef.getPropertyClassType()) {
							if(null != propertyDef.getNestedGenericClass()) {
								ListAndSet<Dtobase> list = new ListAndSet<Dtobase>();
								for(JsonNode item : propertyValue) {
									list.add(this.parse(item, propertyDef.getNestedGenericClass()));
								}
	
								propertyDef.write(dto, list);
								dto.addAssignedField(propertyDef.getName());
							}
						}
					}
					catch(IllegalArgumentException ex) {
						dto.addWrongField(propertyDef.getName());
					}
				}
			}

			return (T)dto;
		}
		catch(Exception ex) {
			throw new RuntimeException("JSON parsing failed", ex);
		}
	}

	/**
	 * Parses JSON data into a list of domain objects
	 * @param data JSON data
	 * @param type domain object
	 * @return parsed domain/DTO object collection
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Dtobase> Iterable<T> parseList(JsonNode data, Class<?> type) {
		List<T> list = new ArrayList<T>();
		for(JsonNode item : data)
		{
			list.add((T)this.parse(item, type));
		}

		return list;
	}

	private Object parseValue(JsonNode json, PropertyMeta propertyDef) throws Exception {
		if(json.isNull()) {
			return null;
		}

		String text = json.asText();
		if(text.length() == 0) {
			return null;
		}

		return propertyDef.getParser().parseTextValue(propertyDef.getPropertyClass(), text);
	}
}