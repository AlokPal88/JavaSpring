package org.jeneva.spring;

import java.io.IOException;
import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.jeneva.Dtobase;
import org.jeneva.IJsonParser;
import org.jeneva.impl.JsonParser;
import org.jeneva.impl.JenevaContext;

/**
 * Represents JSON formatter above the standard Jackson formatter
 */
public class JenevaJsonConverter extends MappingJackson2HttpMessageConverter {

	private IJsonParser jsonParser = new JsonParser(JenevaContext.current());

	@Override
	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		Class<?> theType = (Class<?>)type;
		Object data = null;

		if(Dtobase.class.isAssignableFrom(theType)) {
			JsonNode jsonTree = (JsonNode)super.read(JsonNode.class, contextClass, inputMessage);
			data = this.jsonParser.parse(jsonTree, theType);
		}
		else {
			data = super.read(type, contextClass, inputMessage);
		}

		return data;
	}
}