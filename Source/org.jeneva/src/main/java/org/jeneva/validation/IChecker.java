package org.jeneva.validation;

import java.util.Collection;

import org.jeneva.Dtobase;

/**
 * Defines validation checking routines
 */
public interface IChecker {

	/**
	 * Checks if field value is assigned
	 */
	boolean isAssigned(String fieldName, Dtobase target);

	/**
	 * Checks if field value was correctly parsed
	 */
	boolean isValidFormat(String fieldName, Dtobase target);

	/**
	 * Checks if field value is NULL
	 */
	boolean isNull(Object value);

	/**
	 * Checks if field value equals to m
	 */
	<T> boolean isEqualTo(T m, T value);

	/**
	 * Checks if field value is equal to one of the values
	 */
	<T> boolean isEqualToOneOf(T[] values, T value);

	/**
	 * Checks if field value is empty string
	 */
	boolean isEmptyString(String value);

	/**
	 * Checks if field value length is between min and max values
	 */
	boolean isLengthBetween(int min, int max, String value);

	/**
	 * Checks if field value is less than or equal to 'm'. Comparable<?>.
	 */
	<T extends Comparable<T>> boolean isLessOrEqualTo(T m, T value);

	/**
	 * Checks if field value is less than 'm'. Comparable<?>.
	 */
	<T extends Comparable<T>> boolean isLessThan(T m, T value);

	/**
	 * Checks if field value is greater than or equal to 'm'. Comparable<?>.
	 */
	<T extends Comparable<T>> boolean isGreaterOrEqualTo(T m, T value);

	/**
	 * Checks if field value is greater than 'm'. Comparable<?>.
	 */
	<T extends Comparable<T>> boolean isGreaterThan(T m, T value);

	/**
	 * Checks if field value collection size is between min and max values inclusively (field must implement Comparable<?>)
	 */
	boolean isCountBetween(int min, int max, Collection<?> value);

	/**
	 * Checks if field value qualifies to regular expression
	 */
	boolean isRegexpr(String expr, String value);
}