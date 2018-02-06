package org.jeneva.validation;

import java.util.List;

/**
 * Defines list of validation failures routines
 */
public interface IFailureList extends List<Failure> {

	/**
	 * The highest severity in the list of failures
	 */
	Severity getSeverity();
	
	/**
	 * The highest severity in the list of failures
	 * @param value severity
	 */
	void setSeverity(Severity value);

	/**
	 * True if list is empty
	 */
	boolean isEmpty();

	/**
	 * Adds a new failure object to the list
	 * @param item failure object
	 */
	void fail(Failure item);

	/**
	 * Adds a new failure object to the list
	 * @param key key of the failure
	 * @param text text of the failure
	 */
	void fail(String key, String text);

	/**
	 * Adds a new failure object to the list if condition is true
	 * @param condition condition
	 * @param key key of the failure
	 * @param text text of the failure
	 */
	void failIf(boolean condition, String key, String text);
}