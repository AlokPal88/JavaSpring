package org.myclients.validation.common.impl;

import org.myclients.validation.common.IValidationContext;
import org.jeneva.IJenevaContext;
import org.jeneva.validation.impl.JenevaValidationContext;

public class ValidationContext extends JenevaValidationContext implements IValidationContext {

	public ValidationContext(IJenevaContext context) {
		super(context);
	}

	@Override
	public void required() {

		this.assigned("is required");
		if (this.isFieldValid() && this.isValidFormat())
		{
			this.notNull("is required");
		}
	}

	@Override
	public void missing() {

		this.notAssigned("must be empty");
	}

	@Override
	public void number() {

		this.validFormat("must be valid number");
	}

	@Override
	public void date() {

		this.validFormat("must be valid date");
	}

	@Override
	public void floating() {

		this.validFormat("must be valid floating point number");
	}

	@Override
	public void email() {

		this.regex("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", "must be valid Email");
	}

	@Override
	public void text() {

		this.stringLengthBetween(3, 20, "must be between 3 and 20 characters");
	}

	@Override
	public void trueFalse() {

		this.validFormat("must be 'yes' or 'no'");
	}
}