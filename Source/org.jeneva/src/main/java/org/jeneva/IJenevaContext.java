package org.jeneva;

import org.jeneva.impl.PropertyMeta;
import org.jeneva.validation.IChecker;
import org.jeneva.validation.IMath;
import org.jeneva.validation.IPathHelper;

/**
 * Defines Jeneva context methods
 */
public interface IJenevaContext {

	/**
	 * Gets singleton instance of the PathHelpr class
	 */
	public IPathHelper getPathHelper();

	/**
	 * Gets singleton instance of the Checker class
	 */
	public IChecker getChecker();

	/**
	 * Gets singleton instance of the Math class
	 */
	public IMath getMath();

	/**
	 * Generates an array of ProperyMeta class from a domain/DTO class
	 * @param type domain class
	 */
	public PropertyMeta[] getPropertyDefs(Class<?> type);

	/**
	 * Creates a property parser instance based on property class
	 * @param name property name
	 * @param propertyClass property class
	 * @param propertyClassType property class type
	 * @param annotation Dto annotation instance
	 * @return parser instance
	 */
	IParser buildParser(String name, Class<?> propertyClass, PropertyMeta.ClassType propertyClassType, Dto annotation);
}
