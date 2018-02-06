package org.myclients.validation.common.impl;

import org.myclients.validation.common.IValidationContext;
import org.myclients.validation.common.IValidationContextFactory;
import org.springframework.stereotype.Service;
import org.jeneva.impl.JenevaContext;

@Service
public class ValidationContextFactory implements IValidationContextFactory {

	@Override
	public IValidationContext getNew() {

		return new ValidationContext(JenevaContext.current());
	}
}