package org.jeneva.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.jeneva.IParser;

/**
 * Defines set of standard java types parsers
 */
public class StandardParsers {

	/**
	 * java.lang.String parser
	 */
	public static final IParser STRING_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return text; }
	};

	/**
	 * java.lang.Long parser
	 */
	public static final IParser LONG_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return Long.parseLong(text); }
	};

	/**
	 * java.lang.Integer parser
	 */
	public static final IParser INTEGER_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return Integer.parseInt(text); }
	};

	/**
	 * java.lang.Short parser
	 */
	public static final IParser SHORT_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return Short.parseShort(text); }
	};

	/**
	 * java.lang.Byte parser
	 */
	public static final IParser BYTE_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return Byte.parseByte(text); }
	};

	/**
	 * java.lang.Double parser
	 */
	public static final IParser DOUBLE_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return Double.parseDouble(text); }
	};

	/**
	 * java.lang.Float parser
	 */
	public static final IParser FLOAT_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return Float.parseFloat(text); }
	};

	/**
	 * java.lang.Boolean parser
	 */
	public static final IParser BOOLEAN_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return Boolean.parseBoolean(text); }
	};

	/**
	 * java.lang.Character parser
	 */
	public static final IParser CHAR_PARSER = new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return new Character(text.charAt(0)); }
	};

	/**
	 * java.util.Date parser
	 */
	public static final IParser DATE_PARSER =  new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { Long miliseconds = Long.parseLong(text); return new Date(miliseconds); }
	};

	/**
	 * java.math.BigInteger parser
	 */
	public static final IParser BIGINTEGER_PARSER =  new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return BigInteger.valueOf(Long.parseLong(text)); }
	};

	/**
	 * java.math.BigDecimal parser
	 */
	public static final IParser BIGDECIMAL_PARSER =  new IParser() {
		@Override
		public Object parseTextValue(Class<?> type, String text) { return BigDecimal.valueOf(Double.parseDouble(text)); }
	};


	public static final IParser ENUM_PARSER = new IParser() {

		@SuppressWarnings("unchecked")
		@Override
		public Object parseTextValue(Class<?> type, String text) throws IllegalArgumentException {
			try {
				@SuppressWarnings("rawtypes")
				Class<? extends Enum> enumType = (Class<? extends Enum>)type;
				return (Object)Enum.valueOf(enumType, text);
			}
			catch(Exception ex) {
				throw new IllegalArgumentException(ex);
			}
		}
	};
}
