package org.myclients.validation.common.impl;

import org.myclients.domain.Client;
import org.myclients.validation.IClientValidator;
import org.myclients.validation.common.IValidationContext;
import org.myclients.validation.common.IValidationContextFactory;
import org.myclients.validation.common.IValidationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationFacade implements IValidationFacade {

	@Autowired
	public IValidationContextFactory contextFactory;

	@Autowired
	public IClientValidator clientValidator;

	// @Autowired
	// other validators

	@Override
	public void assertClientForSave(Client target) {

		IValidationContext context = this.contextFactory.getNew();
		context.setTarget(target);
		this.clientValidator.validateForSave(target, context);
		context.assertValid();
	}

	@Override
	public void assertClientForUpdate(Client target) {

		IValidationContext context = this.contextFactory.getNew();
		context.setTarget(target);
		this.clientValidator.validateForUpdate(target, context);
		context.assertValid();
	}

	@Override
	public void assertClientExists(Client item) {

		if (item == null)
		{
			IValidationContext context = this.contextFactory.getNew();
			context.fail("Client does not exist");
			context.assertValid();
		}
	}

	@Override
	public void assertMoreThanOneClient(long count) {

		if (count == 1)
		{
			IValidationContext context = this.contextFactory.getNew();
			context.fail("Cannot delete the only client");
			context.assertValid();
		}
	}
}