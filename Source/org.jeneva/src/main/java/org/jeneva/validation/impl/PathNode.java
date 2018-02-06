package org.jeneva.validation.impl;

import org.jeneva.Dtobase;

/**
 * Represents property path node
 */
public class PathNode {

	/**
	 * Initializes new instance of the PathNode class with name
	 */
	public PathNode(String name) {
		this.name = name;
	}

	private Integer index;
	private String name;
	private Dtobase target;

	/**
	 * Gets node index (valid for arrays and lists)
	 */
	public Integer getIndex()				{ return this.index; }

	/**
	 * Sets node index (valid for arrays and lists)
	 */
	public void setIndex(Integer value)		{ this.index = value; }

	/**
	 * Gets node name
	 */
	public String getName()					{ return this.name; }

	/**
	 * Sets node target object
	 * @param value
	 */
	public void setTarget(Dtobase value)	{ this.target = value; }

	/**
	 * Gets node target object
	 * @return
	 */
	public Dtobase getTarget()				{ return this.target; }
}