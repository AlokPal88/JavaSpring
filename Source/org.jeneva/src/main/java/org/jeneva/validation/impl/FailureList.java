package org.jeneva.validation.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.jeneva.validation.Failure;
import org.jeneva.validation.IFailureList;
import org.jeneva.validation.Severity;

/**
 * Represents list of failures
 */
public class FailureList extends ArrayList<Failure> implements IFailureList {

	private static final long serialVersionUID = 1L;
	private Severity severity = Severity.None;

	/**
	 * Initializes new instance of the FailureList class
	 */
	public FailureList() {
		super();
	}

	/**
	 * Initializes new instance of the FailureList class
	 * @param initialCapacity initial capacity of th list
	 */
	public FailureList(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Initializes new instance of the FailureList class
	 * @param c the collection whose elements are copied to the new list
	 */
	public FailureList(Collection<Failure> c) {
		super(c);
	}

	/**
	 * Gets the highest severity in the list of failures
	 */
	@Override
	public Severity getSeverity()								{ return this.severity; }

	/**
	 * Sets the highest severity in the list of failures
	 */
	@Override
	public void setSeverity(Severity value)						{ this.severity = value; }

	/**
	 * True if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return 0 == this.size();
	}

	/**
	 * Adds a new failure object to the list
	 * @param item failure object
	 */
	@Override
	public void fail(Failure item) {
		this.add(item);
	}

	/**
	 * Adds a new failure object to the list
	 * @param key key/path of the failure
	 * @param text text of the failure
	 */
	@Override
	public void fail(String key, String text) {
		this.fail(new Failure(key, text));
	}

	/**
	 * Adds a new failure object to the list if condition is true
	 * @param condition condition
	 * @param key key/path of the failure
	 * @param text text of the failure
	 */
	@Override
	public void failIf(boolean condition, String key, String text) {
		if (condition) {
			this.fail(key, text);
		}
	}
}