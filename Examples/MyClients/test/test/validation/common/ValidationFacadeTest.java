package test.validation.common;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.myclients.domain.Client;
import org.myclients.validation.IClientValidator;
import org.myclients.validation.common.IValidationContext;
import org.myclients.validation.common.IValidationContextFactory;
import org.myclients.validation.common.impl.ValidationFacade;

public class ValidationFacadeTest {

	private IValidationContext context;
	private IClientValidator clientValidator;
	private IValidationContextFactory contextFactory;
	private ValidationFacade target;

	@Before
	public void setUp() throws Exception {
		this.context = mock(IValidationContext.class);
		this.clientValidator = mock(IClientValidator.class);
		this.contextFactory = mock(IValidationContextFactory.class);
		this.target = new ValidationFacade();
		this.target.clientValidator = this.clientValidator;
		this.target.contextFactory = this.contextFactory;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void assertClientForSaveTest() {
		Client data = new Client(); data.setId(1111);
		when(this.contextFactory.getNew()).thenReturn(this.context);
		this.target.assertClientForSave(data);
		InOrder inOrder = inOrder(this.context, this.clientValidator, this.contextFactory);
		inOrder.verify(this.context).setTarget(data);
		inOrder.verify(this.clientValidator).validateForSave(data, this.context);
		inOrder.verify(this.context).assertValid();
	}

	@Test
	public void assertClientForUpdateTest()
	{
		Client data = new Client(); data.setId(1111);
		when(this.contextFactory.getNew()).thenReturn(this.context);
		this.target.assertClientForUpdate(data);
		InOrder inOrder = inOrder(this.context, this.clientValidator, this.contextFactory);
		inOrder.verify(this.context).setTarget(data);
		inOrder.verify(this.clientValidator).validateForUpdate(data, this.context);
		inOrder.verify(this.context).assertValid();
	}

	@Test
	public void assertClientExists_NotExists()
	{
		when(this.contextFactory.getNew()).thenReturn(this.context);
		this.target.assertClientExists(null);
		InOrder inOrder = inOrder(this.context);
		inOrder.verify(this.context).fail("Client does not exist");
		inOrder.verify(this.context).assertValid();
	}

	@Test
	public void assertClientExists_Exists()
	{
		Client data = new Client(); data.setId(1111);
		this.target.assertClientExists(data);
		verifyZeroInteractions(this.context);
	}

	@Test
	public void assertMoreThanOneClient_2()
	{
		this.target.assertMoreThanOneClient(2);
		verifyZeroInteractions(this.context);
	}

	@Test
	public void assertMoreThanOneClient_1()
	{
		when(this.contextFactory.getNew()).thenReturn(this.context);
		this.target.assertMoreThanOneClient(1);
		InOrder inOrder = inOrder(this.context);
		inOrder.verify(this.context).fail("Cannot delete the only client");
		inOrder.verify(this.context).assertValid();
	}
}