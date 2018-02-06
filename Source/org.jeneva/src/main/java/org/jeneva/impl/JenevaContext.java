package org.jeneva.impl;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.jeneva.Dto;
import org.jeneva.IParser;
import org.jeneva.IJenevaContext;
import org.jeneva.validation.IChecker;
import org.jeneva.validation.IMath;
import org.jeneva.validation.IPathHelper;
import org.jeneva.validation.impl.Checker;
import org.jeneva.validation.impl.Math;
import org.jeneva.validation.impl.PathHelper;

/**
 * Represents Jeneva context object (used internally to store context data)
 */
public class JenevaContext implements IJenevaContext{

	private static final IJenevaContext CURRENT = new JenevaContext();

	/**
	 * gets current Jeneva context
	 */
	public static IJenevaContext current() {
		return CURRENT;
	}

	private final Map<Class<?>, PropertyMeta[]> PROPERTY_DEF_MAP = new HashMap<Class<?>, PropertyMeta[]>();

	private final Class<String> STRING_TYPE = String.class;
	private final Class<Long> LONG_TYPE = Long.class;
	private final Class<Integer> INTEGER_TYPE = Integer.class;
	private final Class<Short> SHORT_TYPE = Short.class;
	private final Class<Byte> BYTE_TYPE = Byte.class;
	private final Class<Double> DOUBLE_TYPE = Double.class;
	private final Class<Float> FLOAT_TYPE = Float.class;
	private final Class<Boolean> BOOLEAN_TYPE = Boolean.class;
	private final Class<Character> CHAR_TYPE = Character.class;
	private final Class<Date> DATE_TYPE = Date.class;
	private final Class<BigDecimal> BIGDECIMAL_TYPE= BigDecimal.class;
	private final Class<BigInteger> BIGINTEGER_TYPE= BigInteger.class;

	private final Class<?> LONG_PRIMITIVE = long.class;
	private final Class<?> INTEGER_PRIMITIVE = int.class;
	private final Class<?> SHORT_PRIMITIVE = short.class;
	private final Class<?> BYTE_PRIMITIVE = byte.class;
	private final Class<?> DOUBLE_PRIMITIVE = double.class;
	private final Class<?> FLOAT_PRIMITIVE = float.class;
	private final Class<?> BOOLEAN_PRIMITIVE = boolean.class;
	private final Class<?> CHAR_PRIMITIVE = char.class;

	private final PropertyMetaFactory propertyMetaFactory;
	private final IPathHelper pathHelper;
	private final IChecker checker;
	private final IMath math;

	private JenevaContext(){
		this.propertyMetaFactory = new PropertyMetaFactory(this);
		this.pathHelper = new PathHelper();
		this.checker = new Checker();
		this.math = new Math();
	}

	/**
	 * Gets singleton instance of the PathHelpr class
	 */
	public IPathHelper getPathHelper() {
		return this.pathHelper;
	}

	/**
	 * Gets singleton instance of the Checker class
	 */
	public IChecker getChecker() {
		return this.checker;
	}

	/**
	 * Gets singleton instance of the Math class
	 */
	public IMath getMath() {
		return this.math;
	}

	/**
	 * Generates an array of ProperyMeta class from a domain class
	 * @param type domain class
	 */
	public PropertyMeta[] getPropertyDefs(Class<?> type) {
		PropertyMeta[] defs = this.PROPERTY_DEF_MAP.get(type);
		if(null != defs) {
			return defs;
		}

		synchronized(this) {
			defs = this.PROPERTY_DEF_MAP.get(type);
			if(null != defs) {
				return defs;
			}

			PropertyDescriptor[] properties = BeanUtils.getPropertyDescriptors(type);
			defs = new PropertyMeta[properties.length];
			for(int i = 0; i < properties.length; i++)
			{
				defs[i] = this.propertyMetaFactory.create(properties[i]);
			}

			PROPERTY_DEF_MAP.put(type, defs);
		}

		return defs;
	}

	/**
	 * Creates a property parser instance based on property class
	 * @param name property name
	 * @param propertyClass property class
	 * @param propertyClassType property class type
	 * @param annotation Dto annotation instance
	 * @return parser instance
	 */
	public IParser buildParser(String name, Class<?> propertyClass, PropertyMeta.ClassType propertyClassType, Dto annotation) {

		if(PropertyMeta.ClassType.Value != propertyClassType) {
			return null;
		}

		Class<?> type = propertyClass;
		try {

			if(IParser.class != annotation.parser()) {
				return annotation.parser().newInstance();
			}

			IParser parser = null;
			if(this.STRING_TYPE == type) {
				parser = StandardParsers.STRING_PARSER;
			}
			else if(this.LONG_TYPE == type || this.LONG_PRIMITIVE == type) {
				parser = StandardParsers.LONG_PARSER;
			}
			else if(this.INTEGER_TYPE == type || this.INTEGER_PRIMITIVE == type) {
				parser = StandardParsers.INTEGER_PARSER;
			}
			else if(this.DOUBLE_TYPE == type || this.DOUBLE_PRIMITIVE == type) {
				parser = StandardParsers.DOUBLE_PARSER;
			}
			else if(this.BIGINTEGER_TYPE == type) {
				parser = StandardParsers.BIGINTEGER_PARSER;
			}
			else if(this.BIGDECIMAL_TYPE == type) {
				parser = StandardParsers.BIGDECIMAL_PARSER;
			}
			else if(this.DATE_TYPE == type) {
				parser = StandardParsers.DATE_PARSER;
			}
			else if(this.BOOLEAN_TYPE == type || this.BOOLEAN_PRIMITIVE == type) {
				parser = StandardParsers.BOOLEAN_PARSER;
			}
			else if(this.SHORT_TYPE == type || this.SHORT_PRIMITIVE == type) {
				parser = StandardParsers.SHORT_PARSER;
			}
			else if(this.BYTE_TYPE == type || this.BYTE_PRIMITIVE == type) {
				parser = StandardParsers.BYTE_PARSER;
			}
			else if(this.FLOAT_TYPE == type || this.FLOAT_PRIMITIVE == type) {
				parser = StandardParsers.FLOAT_PARSER;
			}
			else if(this.CHAR_TYPE == type || this.CHAR_PRIMITIVE == type) {
				parser = StandardParsers.CHAR_PARSER;
			}
			else if(type.isEnum()) {
				parser = StandardParsers.ENUM_PARSER;
			}

			return parser;
		}
		catch (Exception ex) {
			throw new RuntimeException(
				"Unable to find parser for property: " + name + ", of type: " + type.getName() +
				". Property class either must derive from Dtobase or You must setup custom parser for this property in the Dto attribute.");
		}
	}
};