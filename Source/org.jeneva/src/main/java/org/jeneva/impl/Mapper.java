package org.jeneva.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jeneva.Dtobase;
import org.jeneva.IChild;
import org.jeneva.IMapper;
import org.jeneva.IJenevaContext;
import org.jeneva.ListAndSet;

/**
 * Represents Mapping and Filtering helper class
 */
public class Mapper implements IMapper {

	private final IJenevaContext context;

	/**
	 * Initializes new instance of the Mapper class
	 * @param context Jeneva context
	 */
	public Mapper(IJenevaContext context) {
		this.context = context;
	}

	/**
	 * Recursively copies fields from source object to the dest object according to Jeneva rules
	 * Only assigned fields are copied.
	 * This method is used for Updates and Merges
	 * @param source source domain/DTO object
	 * @param dest destination domain/DTO object
	 * @param type domain/DTO type
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Dtobase> void mapTo(T source, T dest, Class<?> type)
	{
		if(null == source) { return; }
		try
		{
			PropertyMeta[] properties = this.context.getPropertyDefs(type);
			for(int i = 0; i < properties.length; i++)
			{
				PropertyMeta property = properties[i];
				if(!property.isValid() || !property.isAssigned(source)) {
					continue;
				}

				Object sourceValue = property.read(source);
				Object destValue = property.read(dest);

				if(PropertyMeta.ClassType.Value == property.getPropertyClassType() ||
					null == sourceValue ||
					null == destValue ||
					PropertyMeta.ClassType.CustomType == property.getPropertyClassType() ||
					PropertyMeta.ClassType.CustomTypeCollection == property.getPropertyClassType()) {
					property.write(dest, sourceValue);
				}
				else if(PropertyMeta.ClassType.Class == property.getPropertyClassType()) {
					if(sourceValue.equals(destValue)) {
						this.mapTo((Dtobase)sourceValue, (Dtobase)destValue, property.getPropertyClass());
					}
					else {
						property.write(dest, sourceValue);
					}
				}
				else if(PropertyMeta.ClassType.Collection == property.getPropertyClassType()) {
					Collection<Dtobase> destSet = (Collection<Dtobase>)destValue;
					Collection<Dtobase> sourceSet = (Collection<Dtobase>)sourceValue;
					this.mapToCollection(sourceSet, destSet, property.getNestedGenericClass(), source);
				}
			}
		}
		catch(Exception ex) {
			throw new RuntimeException(
				"Mapping failed from '" + source.getClass().getName() +
				"' to '" + dest.getClass().getName() + "'", ex);
		}
	}

	/**
	 * Recursively copies objects' fields from source collection to the dest collection according to Jeneva rules.
	 * This method is smart enough to correlate objects to objects based on the overridden equals() method.
	 * Only assigned fields are copied.
	 * This method is used for Updates and Merges
	 * @param source source domain/DTO object collection
	 * @param dest destination domain/DTO object collection
	 * @param type domain/DTO object type
	 * @param parent parent domain/DTO object
	 */
	@Override
	public <T extends Dtobase> void mapToCollection(Collection<T> source, Collection<T> dest, Class<?> type, T parent)
	{
		List<T> destItems = new ArrayList<T>();
		for(T item : dest) {
			destItems.add(item);
		}

		dest.clear();
		for(T item : source) {
			T matchedDestItem = null;
			for(T destItem : destItems) {
				if(destItem.equals(item)) { matchedDestItem = destItem; break; }
			}

			if(null != matchedDestItem) {
				this.mapTo(item, matchedDestItem, type);
				dest.add(matchedDestItem);
			}
			else {
				dest.add(item);
				if(item instanceof IChild) {
					((IChild) item).connectToParent(parent);
				}
			}
		}
	}

	/**
	 * Recursively goes through the source object's collection fields and assigns back-reference if needed.
	 * This method relies on the IChild interface implemented by domain classes.
	 * This method is used for Inserts
	 * @param source source domain/DTO object collection
	 * @param type source domain/DTO object type
	 */
	@Override
	public <T extends Dtobase> void mapCollection(Iterable<T> source, Class<?> type) {
		for(T item : source) {
			this.map(item, type);
		}
	}

	/**
	 * Recursively goes through the source object's fields and assigns back-reference if needed.
	 * This method relies on the IChild interface implemented by domain classes.
	 * This method is used for Inserts
	 * @param source source domain/DTO object
	 * @param type source domain/DTO object type
	 */
	@Override
	public <T extends Dtobase> void map(T source, Class<?> type)
	{
		try
		{
			PropertyMeta[] properties = this.context.getPropertyDefs(type);
			for(int i = 0; i < properties.length; i++)
			{
				PropertyMeta property = properties[i];
				if(!property.isValid()) {
					continue;
				}

				Object sourceValue = property.read(source);
				if(null != sourceValue) {
					if(PropertyMeta.ClassType.Class == property.getPropertyClassType()) {
						this.map((Dtobase)sourceValue, property.getPropertyClass());
					}
					else if(PropertyMeta.ClassType.Collection == property.getPropertyClassType()) {
						Iterable<?> sourceSet = (Iterable<?>)sourceValue;
						for(Object item : sourceSet) {
							this.map((Dtobase)item, property.getNestedGenericClass());
							if(item instanceof IChild) {
								((IChild) item).connectToParent(source);
							}
						}
					}
				}
			}
		}
		catch(Exception ex) {
			throw new RuntimeException("Mapping failed. Type is - '" + source.getClass().getName() + "'", ex);
		}
	}

	/**
	 * Recursively goes through fields of each object in collection and copies selectively field values to the returned object.
	 * Not all field values are copied. The selection is based on the @Dto annotation value and nestedValue fields,
	 * and the level parameter.
	 * @param items collection of domain/DTO objects
	 * @param type domain/DTO object type
	 * @param level required serialization level
	 * @return mapped list
	 */
	@Override
	public <T extends Dtobase> List<T> filterList(Iterable<T> items, Class<?> type, byte level)
	{
		List<T> list = new ListAndSet<T>();
		for(T item : items)
		{
			T dto = this.filter(item, type, level);
			list.add(dto);
		}

		return list;
	}

	/**
	 * Recursively goes through fields of the item object and copies selectively field values to the returned object.
	 * Not all field values are copied. The selection is based on the @Dto annotation value and nestedValue fields,
	 * and the level parameter.
	 * @param item domain/DTO object
	 * @param type domain/DTO object type
	 * @param level required serialization level
	 * @return mapped object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Dtobase> T filter(T item, Class<?> type, byte level)
	{
		try {
			if(null == item) {
				return null;
			}

			Dtobase dto = (Dtobase)type.newInstance();
			PropertyMeta[] properties = this.context.getPropertyDefs(type);
			for(int i = 0; i < properties.length; i++)
			{
				PropertyMeta property = properties[i];
				if(!property.isValid() || !property.hasLevel(level)) {
					continue;
				}

				Object value = property.read(item);
				if(PropertyMeta.ClassType.Value == property.getPropertyClassType() ||
					PropertyMeta.ClassType.CustomType == property.getPropertyClassType() ||
					PropertyMeta.ClassType.CustomTypeCollection == property.getPropertyClassType()) {
					property.write(dto, value);
				}
				else if(PropertyMeta.ClassType.Class == property.getPropertyClassType()) {
					byte nestedLevel = property.getDtoNestedLevel();
					if(property.isDtoDynamic() &&
						level != property.getDtoLevel())
					{
						nestedLevel = (byte)(nestedLevel + level - property.getDtoLevel());
					}

					property.write(dto,
						this.filter((Dtobase)value, property.getPropertyClass(), nestedLevel));
				}
				else if(PropertyMeta.ClassType.Collection == property.getPropertyClassType()) {
					byte nestedLevel = property.getDtoNestedLevel();
					if(property.isDtoDynamic() &&
						level != property.getDtoLevel())
					{
						nestedLevel = (byte)(nestedLevel + level - property.getDtoLevel());
					}

					property.write(dto,
						this.filterList((Iterable<Dtobase>)value, property.getNestedGenericClass(), nestedLevel));
				}
			}

			return (T)dto;
		}
		catch(Exception ex) {
			throw new RuntimeException(
					"Filtering failed. Type is - '" + item.getClass().getName() +
					"'. Level is - '" + level + "'", ex);
		}
	}
}