package org.jeneva.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import org.jeneva.Dto;
import org.jeneva.Dtobase;
import org.jeneva.IJenevaContext;
import org.jeneva.impl.PropertyMeta.ClassType;

/**
 * Represents PropertyMeta class factory
 */
public class PropertyMetaFactory {

	private final IJenevaContext context;

	/**
	 * Initializes new instance of the PropertyMetaFactory class
	 * @param context Jeneva context
	 */
	public PropertyMetaFactory(IJenevaContext context) {
		this.context = context;
	}

	/**
	 * Creates an instance of PropertyMeta class based on property descriptor
	 * @param descriptor property descriptor
	 * @return PropertyMeta class instance
	 */
	public PropertyMeta create(PropertyDescriptor descriptor) {
		PropertyMeta meta = new PropertyMeta(descriptor);
		Method method = descriptor.getReadMethod();
		if(null != method) {
			Dto annotation = method.getAnnotation(Dto.class);
			if(null != annotation) {
				meta.setValid(true);
				meta.setDtoLevel(annotation.value());
				meta.setDtoNestedLevel(annotation.nested());
				meta.setDtoCustomType(annotation.isCustomType());
				meta.setDtoDynamic(annotation.dynamic());
				meta.setPropertyClassType(
						this.buildClassType(meta.getPropertyClass(), annotation));
				meta.setNestedGenericClass(
						this.buildNestedGenericClass(meta.getPropertyClassType(), method));
				meta.setParser(
						this.context.buildParser(meta.getName(), meta.getPropertyClass(), meta.getPropertyClassType(), annotation));
			}
		}

		return meta;
	}

	private PropertyMeta.ClassType buildClassType(Class<?> propertyClass, Dto annotation) {
		PropertyMeta.ClassType propertyClassType = ClassType.Value;
		if(Dtobase.class.isAssignableFrom(propertyClass)) {
			propertyClassType = annotation.isCustomType() ? ClassType.CustomType : ClassType.Class;
		}
		else if (Collection.class.isAssignableFrom(propertyClass)) {
			propertyClassType = annotation.isCustomType() ? ClassType.CustomTypeCollection : ClassType.Collection;
		}

		return propertyClassType;
	}

	private Class<?> buildNestedGenericClass(PropertyMeta.ClassType propertyClassType, Method method) {
		Class<?> nestedGenericClass = null;
		if(ClassType.Collection == propertyClassType || ClassType.CustomTypeCollection == propertyClassType) {
			Type genericType = method.getGenericReturnType();
			if(genericType instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType)genericType;
				Type[] args = pt.getActualTypeArguments();
				nestedGenericClass = (Class<?>)args[0];
			}
		}

		return nestedGenericClass;
	}
}