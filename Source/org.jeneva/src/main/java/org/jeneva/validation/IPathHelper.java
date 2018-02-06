package org.jeneva.validation;

import java.util.Deque;

import org.jeneva.Dtobase;
import org.jeneva.validation.impl.PathNode;

/**
 * Defines methods for building property path
 */
public interface IPathHelper {

	/**
	 * Builds property path using existing path and a nested property name
	 * @param path existing path
	 * @param name nested property name
	 * @return property path text
	 */
	String buildPath(Deque<PathNode> path, String name);

	/**
	 * Creates a new instance of the path list
	 */
	Deque<PathNode> createNew();

	/**
	 * Adds nested field into the path
	 */
	void addNested(Deque<PathNode> path, String name);

	/**
	 * Removes topmost field from the path
	 */
	void removeNested(Deque<PathNode> path);

	/**
	 * Sets index of the target
	 */
	void setIndex(Deque<PathNode> path, int index);

	/**
	 * Sets top node target object
	 * @param path existing path
	 * @param target target object
	 */
	void setTopNodeTarget(Deque<PathNode> path, Dtobase target);

	/**
	 * Gets top node target object
	 * @param path existing path
	 * @return
	 */
	Dtobase getTopNodeTarget(Deque<PathNode> path);
}