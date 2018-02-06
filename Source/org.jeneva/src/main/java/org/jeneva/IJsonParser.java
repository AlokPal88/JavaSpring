package org.jeneva;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * Defines JSON parser methods
 */
public interface IJsonParser {

	/**
	 * Parses JSON data into domain object
	 * @param data json data
	 * @param type the domain object type
	 * @return
	 */
	<T extends Dtobase> T parse(JsonNode data, Class<?> type);

	/**
	 * Parses JSON data into a list of domain objects
	 * @param data json data
	 * @param type the domain object type
	 * @return
	 */
	<T extends Dtobase> Iterable<T> parseList(JsonNode data, Class<?> type);
}