package org.jeneva.validation;

import org.jeneva.Dtobase;

/**
 * Defines Validation context routines
 *
 */
public interface IJenevaValidationContext {

	/**
	 * False, if at least on failure is recorded, otherwise true
	 */
	boolean isValid();

	/**
	 * False, if at least on failure is recorded for the current target, otherwise true
	 */
	boolean isTargetValid();

	/**
	 * False, if at least on failure is recorded for the current field, otherwise true
	 */
	boolean isFieldValid();

	/**
	 * Sets current target object
	 */
	void setTarget(Dtobase value);

	/**
	 * Sets current index (used for indexed properties).
	 * For ex. current target is 'Children' and index is '7' and field 'Name' - failure would have this path - 'children[7].name'.
	 */
	void setIndex(int index);

	/**
	 * Sets current field (property) name
	 */
	void setField(String name, Object value);

	/**
	 * Propagates current field to path.
	 * For ex. if current field is 'User', call AddNested(), then call SetField("Name", null) - the failure path would be - User.Name.
	 * Usually after AddNested() call, SetTarget() must be called as well.
	 */
	void addNested();

	/**
	 * Goes one node back in path
	 */
	void removeNested();

	/**
	 * Registers failure without path
	 * @param msg
	 */
	void failRoot(String key, String msg);

	/**
	 * Registers failure using current path
	 * @param msg
	 */
	void fail(String msg);

	/**
	 * Registers failure
	 * @param failure
	 */
	void fail(Failure failure);

	/**
	 * True if current field is assigned, otherwise false
	 */
	boolean isAssigned();

	/**
	 * True is current field is null, otherwise false
	 */
	boolean isNull();

	/**
	 * True if current field is correctly parsed, otherwise false
	 */
	boolean isValidFormat();

	/**
	 * Validates if current field is assigned (was present in incoming JSON)
	 * @param msg failure message
	 */
	void assigned(String msg);

	/**
	 * Validates if current field value is not assigned by JSON deserializer
	 * @param msg failure message
	 */
	void notAssigned(String msg);

	/**
	 * Validates if current field value is correctly parsed
	 * @param msg failure message
	 */
	void validFormat(String msg);

	/**
	 * Validates if current field value is null
	 * @param msg failure message
	 */
	void nullValue(String msg);

	/**
	 * Validates if current field value is not null
	 * @param msg failure message
	 */
	void notNull(String msg);

	/**
	 * Validates if current field value is equal to the value
	 * @param msg failure message
	 */
	<T> void equalTo(T value, String msg);

	/**
	 * Validates if current field value is not equal to the value
	 * @param msg failure message
	 */
	<T> void notEqualTo(T value, String msg);

	/**
	 * Validates if current field value is equal to one of the values
	 * @param msg failure message
	 */
	<T> void equalToOneOf(T[] values, String msg);

	/**
	 * Validates if current field value is not equal to any of the values
	 * @param msg failure message
	 */
	<T> void notEqualToAnyOf(T[] values, String msg);

	/**
	 * Validates if current field value is not empty string
	 * @param msg failure message
	 */
	void stringNotEmpty(String msg);

	/**
	 * Validates if current field value length (String.length) is between min and max
	 * @param msg failure message
	 */
	void stringLengthBetween(int min, int max, String msg);

	/**
	 * Validates if current field value (Comparable) is less or equal to m
	 * @param msg failure message
	 */
	<T extends Comparable<T>> void lessOrEqualTo(T m, String msg);

	/**
	 * validates if current field value (Comparable) is less than m
	 * @param msg failure message
	 */
	<T extends Comparable<T>> void lessThan(T m, String msg);

	/**
	 * Validates if current field value (Comparable) is greater or equal to m
	 * @param msg failure message
	 */
	<T extends Comparable<T>> void greaterOrEqualTo(T m, String msg);

	/**
	 * Validates if current field value (Comparable) is greater than m
	 * @param msg failure message
	 */
	<T extends Comparable<T>> void greaterThan(T m, String msg);

	/**
	 * Validates if current field value count (Collection.size()) is between min and max count
	 * @param msg failure message
	 */
	void countIsBetween(int min, int max, String msg);

	/**
	 * Validates if current field value follows the regular expression
	 * @param msg failure message
	 */
	void regex(String expr, String msg);

	/**
	 * Throws ValidationException if at least one failure was registered
	 */
	void assertValid();

	/**
	 * Gets Math helper object
	 */
	IMath getMath();
}