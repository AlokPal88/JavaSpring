package org.jeneva;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents base DTO class.
 * You must derive your domain/dto classes from Dtobase to make Jeneva features available.
 */
public class Dtobase {
	private Set<String> assignedFields;
	private Set<String> wrongFields;

	/**
	 * Adds field to the Set of assigned fields
	 * @param field name of the field
	 */
	public void addAssignedField(String field) {
		if(null == this.assignedFields) {
			this.assignedFields = new HashSet<String>();
		}

		this.assignedFields.add(field);
	}

	/**
	 * Adds field to the set of wrong fields (failed to be parsed)
	 * @param field name of the field
	 */
	public void addWrongField(String field) {
		if(null == this.wrongFields) {
			this.wrongFields = new HashSet<String>();
		}

		this.wrongFields.add(field);
	}

	/**
	 * Checks if a field is assigned
	 * @param field name of the field
	 * @return
	 */
	public boolean isFieldAssigned(String field) {
		return null == this.assignedFields
				? false
				: this.assignedFields.contains(field);
	}

	/**
	 * Checks if a field is wrong (failed to be parsed)
	 * @param field name of the field
	 * @return
	 */
	public boolean isFieldWrong(String field) {
		return null == this.wrongFields
				? false
				: this.wrongFields.contains(field);
	}

	/**
	 * Returns set of assigned field names (fields that were assigned by JSON deserializer)
	 * @return set of field names
	*/
	public Set<String> readAssignedFields()
	{
		return this.assignedFields;
	}

	/**
	 * Returns set of wrong fields (fields that failed to be parsed)
	 * @return set of field names</returns>
	 */
	public Set<String> readWrongFields()
	{
		return this.wrongFields;
	}
}