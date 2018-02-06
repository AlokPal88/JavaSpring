package org.jeneva;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents marker annotation for DTO class properties
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dto {

	/**
	 * Level of serialization applied to this property
	 */
	byte value() default 1;

	/**
	 * If the current property represents nested domain object, then this field defines
	 * the Level of serialization applied to the child properties of the domain object
	 */
	byte nested() default 1;

	/**
	 * You can provide your own parser. Creating custom parser is simple.
	 * You have to implement the IParser interface.
	 * If parsing is impossible due to wrong format custom parser must throw some Exception
	 * @return
	 */
	Class<? extends IParser> parser() default IParser.class;

	/**
	 * Must be set to true if current property is Custom Hibernate Type.
	 * @return
	 */
	boolean isCustomType() default false;

	/**
	 * Gets or sets value that indicates if nested serialization level is resolved dynamically.
	 * Nested level can be dynamic if you are trying to serialize parent object using some level higher than the level indicated in the DtoAttribute.Value.
	 * In this case the nested level is calculated at runtime based on difference between the required level and the DtoAttribute.Value.
	 * This difference is added to the DtoAttribute.Nested serialization level. This behaviour works only if Dynamic property is true.
	 * @return
	 */
	boolean dynamic() default false;
}