package org.jeneva;

/**
 * Defines parseing method (used for parsing field values).
 * Implement this interface for custom type properties, and mark it in the DtoAttribute.
 */
public interface IParser {

	/**
	 * must throw IllegalArgumentException if parsing failed
	 * @param type custom type
	 * @param text json txt
	 * @return parsed value
	 */
	Object parseTextValue(Class<?> type, String text) throws IllegalArgumentException;
}