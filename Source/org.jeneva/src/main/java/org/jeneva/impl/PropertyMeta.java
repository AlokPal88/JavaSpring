package org.jeneva.impl;

import java.beans.PropertyDescriptor;

import org.jeneva.Dtobase;
import org.jeneva.IParser;

/**
 * Represents Domain object Property metadata
 */
public class PropertyMeta {

	/**
	 * Defines domain/DTO property type, used by Jeneva internally
	 */
	public enum ClassType { Class, CustomType, Collection, CustomTypeCollection, Value }

	private PropertyDescriptor descriptor;
	private String name;
	private Class<?> propertyClass;
	private ClassType propertyClassType;
	private IParser parser;
	private Class<?> nestedGenericClass;
	private byte dtoLevel;
	private byte dtoNestedLevel;
	private boolean dtoCustomType;
	private boolean dtoDynamic;
	private boolean validDto;

	/**
	 * initializes a new instance of the PropertyMeta class
	 * @param descriptor
	 * @throws RuntimeException
	 */
	public PropertyMeta(PropertyDescriptor descriptor) throws RuntimeException {
		this.descriptor = descriptor;
		this.name = descriptor.getName();
		this.propertyClass = descriptor.getPropertyType();
	}

	/**
	 * gets property name
	 */
	public String getName()											{ return this.name; }

	/**
	 * Gets property class (type)
	 */
	public Class<?> getPropertyClass()								{ return this.propertyClass; }


	/**
	 * Gets property class type
	 */
	public ClassType getPropertyClassType()							{ return this.propertyClassType; }

	/**
	 * Sets property class type
	 */
	public void setPropertyClassType(ClassType value)				{ this.propertyClassType = value; }


	/**
	 * Gets property inner generic class (used for List<T>, ISet<T>, etc)
	 */
	public Class<?> getNestedGenericClass()							{ return this.nestedGenericClass; }

	/**
	 * Sets property inner generic class (used for List<T>, ISet<T>, etc)
	 */
	public void setNestedGenericClass(Class<?> value)				{ this.nestedGenericClass = value; }


	/**
	 * Gets serialization level assigned to the property
	 */
	public byte getDtoLevel()										{ return this.dtoLevel; }

	/**
	 * Sets serialization level assigned to the property
	 */
	public void setDtoLevel(byte value)								{ this.dtoLevel = value; }


	/**
	 * Gets serialization level assigned to property children
	 */
	public byte getDtoNestedLevel()									{ return this.dtoNestedLevel; }

	/**
	 * Sets serialization level assigned to property children
	 */
	public void setDtoNestedLevel(byte value)						{ this.dtoNestedLevel = value; }


	/**
	 * Returns true if the property is custom type (used for Hibernate custom types)
	 */
	public boolean isDtoCustomType()								{ return this.dtoCustomType; }

	/**
	 * Sets flag if the property is custom type (used for Hibernate custom types)
	 */
	public void setDtoCustomType(boolean value)						{ this.dtoCustomType = value; }


	/**
	 * Returns true if serialization level assigned to the property children is resolved dynamically
	 * @return
	 */
	public boolean isDtoDynamic()									{ return this.dtoDynamic; }

	/**
	 * Sets flag if serialization level assigned to the property children is resolved dynamically
	 */
	public void setDtoDynamic(boolean value)						{ this.dtoDynamic = value; }


	public IParser getParser()										{ return this.parser; }
	public void setParser(IParser value)							{ this.parser = value; }


	/**
	 * Returns true if the property is valid DTO
	 */
	public boolean isValid()										{ return this.validDto; }

	/**
	 * Sets flag if the property is valid DTO
	 */
	public void setValid(boolean value)								{ this.validDto = value; }


	/**
	 * Determines if the property falls into the requested serialization level
	 * @param level requested serialization level
	 * @return true if yes
	 */
	public boolean hasLevel(byte level) {
		return this.dtoLevel <= level;
	}

	/**
	 * Determines if the property instance is assigned by JSON deserializer
	 * @param target domain/DTO object
	 * @return true if assigned
	 */
	public boolean isAssigned(Dtobase target) {
		return target.isFieldAssigned(this.descriptor.getName());
	}

	/**
	 * Assigns value to the property
	 * @param target domain/DTO object
	 * @param value property value
	 * @throws Exception if failed
	 */
	public void write(Dtobase target, Object value) throws Exception {
		this.descriptor.getWriteMethod().invoke(target, value);
	}

	/**
	 * Reads property value
	 * @param target domain/DTO object
	 * @return property value
	 * @throws Exception if failed
	 */
	public Object read(Dtobase target) throws Exception {
		return this.descriptor.getReadMethod().invoke(target);
	}
}