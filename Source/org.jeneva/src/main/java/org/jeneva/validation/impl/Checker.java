package org.jeneva.validation.impl;

import java.util.Collection;

import org.jeneva.Dtobase;
import org.jeneva.validation.IChecker;

/**
 * Represents validation checker helper class
 */
public class Checker implements IChecker {

	/**
	 * Checks if field value is assigned
	 */
	@Override
	public boolean isAssigned(String fieldName, Dtobase target) {
		return target.isFieldAssigned(fieldName);
	}

	/**
	 * Checks if field value was correctly parsed
	 */
	@Override
	public boolean isValidFormat(String fieldName, Dtobase target) {
		return !target.isFieldWrong(fieldName);
	}

	/**
	 * Checks if field value is NULL
	 */
	@Override
	public boolean isNull(Object value) {
		return null == value;
	}

	/**
	 * Checks if field value equals to m
	 */
	@Override
	public <T> boolean isEqualTo(T m, T value) {
		return m.equals(value);
	}

	/**
	 * Checks if field value is equal to one of values
	 */
	@Override
	public <T> boolean isEqualToOneOf(T[] values, T value) {
		boolean success = false;
		for (int i = 0; i < values.length; i++)
		{
			if (value.equals(values[i]))
			{
				success = true;
				break;
			}
		}

		return success;
	}

	/**
	 * Checks if field value is empty string
	 */
	@Override
	public boolean isEmptyString(String value) {
		return value.equals("");
	}

	/**
	 * Checks if field value length is between min and max values
	 */
	@Override
	public boolean isLengthBetween(int min, int max, String value) {
		return value.length() >= min && value.length() <= max;
	}

	/**
	 * Checks if field value is less than or equal to 'm'
	 */
	@Override
	public <T extends Comparable<T>> boolean isLessOrEqualTo(T m, T value) {
		return value.compareTo(m) <= 0;
	}

	/**
	 * Checks if field value is less than 'm'. Comparable.
	 */
	@Override
	public <T extends Comparable<T>> boolean isLessThan(T m, T value) {
		return value.compareTo(m) < 0;
	}

	/**
	 * Checks if field value is greater than or equal to 'm'. Comparable.
	 */
	@Override
	public <T extends Comparable<T>> boolean isGreaterOrEqualTo(T m, T value) {
		return value.compareTo(m) >= 0;
	}

	/**
	 * Checks if field value is greater than 'm'. Comparable.
	 */
	@Override
	public <T extends Comparable<T>> boolean isGreaterThan(T m, T value) {
		return value.compareTo(m) > 0;
	}

	/**
	 * Checks if field value collection size is between min and max values inclusively (field must implement Collection)
	 */
	@Override
	public boolean isCountBetween(int min, int max, Collection<?> value) {
		return value.size() >= min && value.size() <= max;
	}

	/**
	 * Checks if field value qualifies to regular expression
	 */
	@Override
	public boolean isRegexpr(String expr, String value) {
		return value.matches(expr);
	}
}