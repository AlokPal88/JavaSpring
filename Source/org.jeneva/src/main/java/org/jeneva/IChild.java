package org.jeneva;

/**
 * Defines a method for cascaded child domain objects.
 * This interface must be derived by child domain/DTO classes.
 * Used by JSON parser to suggest possible parent object to child.
 */
public interface IChild {

	/**
	 * Connects parent domain object to a child domain object
	 */
	void connectToParent(Object parent);
}
