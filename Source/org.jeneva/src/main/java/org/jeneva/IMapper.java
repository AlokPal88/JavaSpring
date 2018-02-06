package org.jeneva;

import java.util.Collection;
import java.util.List;

/**
 * Defines Mapper class methods
 */
public interface IMapper {

	/**
	 * Recursively copies fields from source object to the dest object according to Jeneva rules
	 * Only assigned fields are copied.
	 * This method is used for Updates and Merges
	 * @param source source domain/DTO object
	 * @param dest destination domain/DTO object
	 */
	<T extends Dtobase> void mapTo(T source, T dest, Class<?> type);

	/**
	 * Recursively copies objects' fields from source collection to the dest collection according to Jeneva rules.
	 * This method is smart enough to correlate objects to objects based on the overridden equals() method.
	 * Only assigned fields are copied.
	 * This method is used for Updates and Merges
	 * @param source source domain/DTO object collection
	 * @param dest destination domain/DTO object collection
	 * @param dest type domain/DTO object type
	 * @param parent parent domain/DTO object
	 */
	<T extends Dtobase> void mapToCollection(Collection<T> source, Collection<T> dest, Class<?> type, T parent);

	/**
	 * Recursively goes through the source object's collection fields and assigns back-reference if needed.
	 * This method relies on the IChild interface implemented by domain classes.
	 * This method is used for Inserts
	 * @param source source domain/DTO object
	 */
	<T extends Dtobase> void mapCollection(Iterable<T> source, Class<?> type);

	/**
	 * Recursively goes through the source object's fields and assigns parent object back-reference if needed.
	 * This method relies on the IChild interface implemented by domain classes.
	 * This method is used for Inserts
	 * @param source source domain/DTO object
	 */
	<T extends Dtobase> void map(T source, Class<?> type);

	/**
	 * Recursively goes through fields of each object in collection and copies selectively field values to the returned object.
	 * Not all field values are copied. The selection is based on the @Dto annotation value and nestedValue fields,
	 * and the level parameter.
	 * @param items collection of domain/DTO objects
	 * @param type domain/DTO object type
	 * @param level required serialization level
	 * @return mapped list
	 */
	<T extends Dtobase> List<T> filterList(Iterable<T> items, Class<?> type, byte level);

	/**
	 * Recursively goes through fields of the item object and copies selectively field values to the returned object.
	 * Not all field values are copied. The selection is based on the @Dto annotation value and nestedValue fields,
	 * and the level parameter.
	 * @param item domain/DTO object
	 * @param type domain/DTO object type
	 * @param level required serialization level
	 * @return mapped object
	 */
	public <T extends Dtobase> T filter(T item, Class<?> type, byte level);
}