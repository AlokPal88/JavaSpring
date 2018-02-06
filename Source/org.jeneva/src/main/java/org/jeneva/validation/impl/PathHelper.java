package org.jeneva.validation.impl;

import java.util.Deque;
import java.util.LinkedList;

import org.jeneva.Dtobase;
import org.jeneva.validation.IPathHelper;

/**
 * Represents property path builder class
 */
public class PathHelper implements IPathHelper {

	/**
	 * Builds property path using existing path and a nested property name
	 * @param path existing path
	 * @param name nested property name
	 * @return property path text
	 */
	@Override
	public String buildPath(Deque<PathNode> path, String name) {

		StringBuilder text = new StringBuilder("");
		for(PathNode current : path)
		{
			boolean written = false;
			String property = current.getName();
			if(null != property) {
				text.append(property);
				written = true;
			}
			
			if (null != current.getIndex())
			{
				text.append('[');
				text.append(current.getIndex());
				text.append(']');
				written = true;
			}

			if (written)
			{
				text.append('.');
			}
		}

		if(null != name) {
			text.append(name);
		}

		return text.toString();
	}

	/**
	 * Creates a new instance of the path list
	 */
	@Override
	public Deque<PathNode> createNew()
	{
		Deque<PathNode> path = new LinkedList<PathNode>();
		path.addLast(new PathNode(null));
		return path;
	}

	@Override
	/**
	 * Adds nested field into the path
	 */
	public void addNested(Deque<PathNode> path, String name) {
		path.addLast(new PathNode(name));
	}

	/**
	 * Removes topmost field from the path
	 */
	@Override
	public void removeNested(Deque<PathNode> path) {
		path.removeLast();
	}

	/**
	 * Sets index of the target
	 */
	@Override
	public void setIndex(Deque<PathNode> path, int index) {
		path.getLast().setIndex(index);
	}

	/**
	 * Sets top node target object
	 * @param path existing path
	 * @param target target object
	 */
	public void setTopNodeTarget(Deque<PathNode> path, Dtobase target) {
		path.getLast().setTarget(target);
	}

	/**
	 * Gets top node target object
	 * @param path existing path
	 * @return
	 */
	public Dtobase getTopNodeTarget(Deque<PathNode> path) {
		return path.getLast().getTarget();
	}
}