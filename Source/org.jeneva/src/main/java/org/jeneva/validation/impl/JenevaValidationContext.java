package org.jeneva.validation.impl;

import java.util.Collection;
import java.util.Deque;

import org.jeneva.Dtobase;
import org.jeneva.IJenevaContext;
import org.jeneva.validation.Failure;
import org.jeneva.validation.IChecker;
import org.jeneva.validation.IFailureList;
import org.jeneva.validation.IMath;
import org.jeneva.validation.IPathHelper;
import org.jeneva.validation.IJenevaValidationContext;
import org.jeneva.validation.ValidationException;

/**
 * Represents basic set of validation routines
 */
public class JenevaValidationContext implements IJenevaValidationContext {

	private final IMath math;
	private final IPathHelper pathHelper;
	private final IChecker checker;
	private final Deque<PathNode> path;

	protected String fieldName;
	protected Object fieldValue;
	protected IFailureList failures;

	private boolean isValid;
	private boolean isTargetValid;
	private boolean isFieldValid;

	/**
	 * Initializes new instance of the ValidationContext class
	 */
	public JenevaValidationContext(IJenevaContext context) {
		this.math = context.getMath();
		this.pathHelper = context.getPathHelper();
		this.checker = context.getChecker();
		this.path = this.pathHelper.createNew();
	}

	/**
	 * False, if at least on failure is recorded, otherwise true
	 */
	@Override
	public boolean isValid() {
		return this.isValid;
	}

	/**
	 * False, if at least on failure is recorded for the current target, otherwise true
	 */
	@Override
	public boolean isTargetValid() {
		return this.isTargetValid;
	}

	/**
	 * False, if at least on failure is recorded for the current field, otherwise true
	 */
	@Override
	public boolean isFieldValid() {
		return this.isFieldValid;
	}

	/**
	 * Sets current target object
	 */
	@Override
	public void setTarget(Dtobase value) {
		this.pathHelper.setTopNodeTarget(this.path, value);
		this.isTargetValid = true;
	}

	/**
	 * Sets current index (used for indexed properties).
	 * For ex. current target is 'Children' and index is '7' and field 'Name' - failure would have this path - 'children[7].name'.
	 */
	@Override
	public void setIndex(int index) {
		this.path.getLast().setIndex(index);
	}

	/**
	 * Sets current field (property) name
	 */
	@Override
	public void setField(String name, Object value) {
		this.fieldValue = value;
		this.fieldName = name;
		this.isFieldValid = true;
		
		if (null != value)
		{
			Dtobase target = this.pathHelper.getTopNodeTarget(this.path);
			target.addAssignedField(this.fieldName);
		}
	}

	/**
	 * Propagates current field to path.
	 * For ex. if current field is 'User', call AddNested(), then call SetField("Name", null) - the failure path would be - User.Name.
	 * Usually after AddNested() call, SetTarget() must be called as well.
	 */
	@Override
	public void addNested() {
		this.pathHelper.addNested(this.path, this.fieldName);
	}

	/**
	 * Goes one node back in path.
	 */
	@Override
	public void removeNested() {
		this.pathHelper.removeNested(this.path);
	}

	/**
	 * Registers failure without path
	 */
	@Override
	public void failRoot(String key, String msg) {
		this.fail(new Failure(key, msg));
	}

	/**
	 * Registers failure using current path
	 */
	@Override
	public void fail(String msg) {
		Failure failure = new Failure(
			this.pathHelper.buildPath(this.path, this.fieldName),
			msg);
		this.fail(failure);
	}

	/**
	 * Registers failure
	 */
	@Override
	public void fail(Failure failure) {
		this.isFieldValid = false;
		this.isTargetValid = false;
		this.isValid = false;
		if (null == this.failures)
		{
			this.failures = new FailureList();
		}
		
		this.failures.fail(failure);
	}

	/**
	 * True if current field is assigned, otherwise false
	 */
	@Override
	public boolean isAssigned() {
		Dtobase target = this.pathHelper.getTopNodeTarget(this.path);
		return this.checker.isAssigned(this.fieldName, target);
	}

	/**
	 * True is current field is null, otherwise false
	 */
	@Override
	public boolean isNull() {
		return this.checker.isNull(this.fieldValue);
	}

	/**
	 * True if current field is correctly parsed, otherwise false
	 */
	@Override
	public boolean isValidFormat() {
		Dtobase target = this.pathHelper.getTopNodeTarget(this.path);
		return this.checker.isValidFormat(this.fieldName, target);
	}

	/**
	 * Validates if current field is assigned (was present in incoming JSON)
	 * @param msg failure message
	 */
	@Override
	public void assigned(String msg) {
		if (!this.isAssigned())
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is not assigned by JSON deserializer
	 * @param msg failure message
	 */
	@Override
	public void notAssigned(String msg) {
		if (this.isAssigned())
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is correctly parsed
	 * @param msg failure message
	 */
	@Override
	public void validFormat(String msg) {
		if (!this.isValidFormat())
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is null
	 * @param msg failure message
	 */
	@Override
	public void nullValue(String msg) {
		if (!this.isNull())
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is not null
	 * @param msg failure message
	 */
	@Override
	public void notNull(String msg) {
		if (this.isNull())
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is equal to the value
	 * @param msg failure message
	 */
	@Override
	public <T> void equalTo(T value, String msg) {
		if (!this.checker.isEqualTo(value, this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is not equal to the value
	 * @param msg failure message
	 */
	@Override
	public <T> void notEqualTo(T value, String msg) {
		if (this.checker.isEqualTo(value, this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is equal to one of the values
	 * @param msg failure message
	 */
	@Override
	public <T> void equalToOneOf(T[] values, String msg) {
		if (!this.checker.isEqualToOneOf(values, this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is not equal to any of the values
	 * @param msg failure message
	 */
	@Override
	public <T> void notEqualToAnyOf(T[] values, String msg) {
		if (this.checker.isEqualToOneOf(values, this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value is not empty string
	 * @param msg failure message
	 */
	@Override
	public void stringNotEmpty(String msg) {
		if (null == this.fieldValue) return;
		if (this.checker.isEmptyString((String)this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value length (String.length) is between min and max
	 * @param msg failure message
	 */
	@Override
	public void stringLengthBetween(int min, int max, String msg) {
		if (null == this.fieldValue) return;
		if (!this.checker.isLengthBetween(min, max, (String)this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value (Comparable) is less or equal to m
	 * @param msg failure message
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Comparable<T>> void lessOrEqualTo(T m, String msg) {
		if (null == this.fieldValue) return;
		if (!this.checker.isLessOrEqualTo(m, (T)this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value (Comparable) is less than m
	 * @param msg failure message
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Comparable<T>> void lessThan(T m, String msg) {
		if (null == this.fieldValue) return;
		if (this.checker.isLessThan(m, (T)this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value (Comparable) is greater or equal to m
	 * @param msg failure message
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Comparable<T>> void greaterOrEqualTo(T m, String msg) {
		if (null == this.fieldValue) return;
		if (!this.checker.isGreaterOrEqualTo(m, (T)this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value (Comparable) is greater than m
	 * @param msg failure message
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Comparable<T>> void greaterThan(T m, String msg) {
		if (null == this.fieldValue) return;
		if (!this.checker.isGreaterThan(m, (T)this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value count (Collection.size()) is between min and max count
	 * @param msg failure message
	 */
	@Override
	public void countIsBetween(int min, int max, String msg) {
		if (null == this.fieldValue) return;
		if (!this.checker.isCountBetween(min, max, (Collection<?>)this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Validates if current field value follows the regular expression
	 * @param msg failure message
	 */
	@Override
	public void regex(String expr, String msg) {
		if (null == this.fieldValue) return;
		if (!this.checker.isRegexpr(expr, (String)this.fieldValue))
		{
			this.fail(msg);
		}
	}

	/**
	 * Throws ValidationException if at least one failure was registered
	 */
	@Override
	public void assertValid() {
		if (null != this.failures)
		{
			throw new ValidationException(this.failures);
		}
	}

	/**
	 * Gets Math helper object
	 */
	@Override
	public IMath getMath() {
		return this.math;
	}
}