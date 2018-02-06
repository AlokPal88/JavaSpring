package org.myclients.validation.common;

import org.jeneva.validation.IJenevaValidationContext;

public interface IValidationContext extends IJenevaValidationContext {

	void required();
	void missing();
	void number();
	void date();
	void floating();
	void email();
	void text();
	void trueFalse();
}