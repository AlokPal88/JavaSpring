package org.jeneva;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Represents common class for List and Set collections. Used internally by JSON deserializer.
 */
public class ListAndSet<T> extends ArrayList<T> implements Set<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a new instance of the ListAndSet<T> class that is empty and has the default initial capacity.
	 */
	public ListAndSet() {
		super();
	}

	/**
	 * Initializes a new instance of the ListAndSet<T> class that is empty and has the specified initial capacity.
	 * @param initialCapacity initial ArrayList capacity
	 */
	public ListAndSet(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Initializes a new instance of the System.Collections.Generic.List<T> class
	 * that contains elements copied from the specified collection and has sufficient
	 * capacity to accommodate the number of elements copied.
	 * @param c source collection
	 */
	public ListAndSet(Collection<? extends T> c) {
		super(c);
	}
}