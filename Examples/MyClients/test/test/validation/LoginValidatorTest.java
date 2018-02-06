package test.validation;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.myclients.domain.Client;
import org.myclients.domain.Login;
import org.myclients.validation.common.IValidationContext;
import org.myclients.validation.impl.LoginValidator;

public class LoginValidatorTest {

	private IValidationContext context;
	private LoginValidator target;

	@Before
	public void setUp() throws Exception {

		this.context = mock(IValidationContext.class);
		this.target = new LoginValidator();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validateForSaveTest() {

		Login data = this.buildData();
		this.target.validateForSave(data, this.context);
		InOrder inOrder = inOrder(this.context);

		inOrder.verify(this.context).setField("id", data.getId());
		inOrder.verify(this.context).missing();

		inOrder.verify(this.context).setField("name", data.getName());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();

		inOrder.verify(this.context).setField("password", data.getPassword());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();

		inOrder.verify(this.context).setField("enabled", data.getEnabled());
		inOrder.verify(this.context).trueFalse();

		inOrder.verify(this.context).setField("client", data.getClient());
		inOrder.verify(this.context).missing();
	}

	@Test
	public void validateForMerge_Update_Test() {

		Login data = this.buildData();
		when(this.context.isAssigned()).thenReturn(true);
		this.target.validateForMerge(data, this.context);
		InOrder inOrder = inOrder(this.context);

		inOrder.verify(this.context).setField("id", data.getId());
		inOrder.verify(this.context).number();

		inOrder.verify(this.context).setField("name", data.getName());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();

		inOrder.verify(this.context).setField("password", data.getPassword());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();

		inOrder.verify(this.context).setField("enabled", data.getEnabled());
		inOrder.verify(this.context).trueFalse();

		inOrder.verify(this.context).setField("client", data.getClient());
		inOrder.verify(this.context).missing();
	}

	@Test
	public void validateForMerge_Save_Test() {

		Login data = this.buildData();
		when(this.context.isAssigned()).thenReturn(false);
		this.target.validateForMerge(data, this.context);
		InOrder inOrder = inOrder(this.context);

		inOrder.verify(this.context).setField("id", data.getId());

		inOrder.verify(this.context).setField("name", data.getName());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();

		inOrder.verify(this.context).setField("password", data.getPassword());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();

		inOrder.verify(this.context).setField("enabled", data.getEnabled());
		inOrder.verify(this.context).trueFalse();

		inOrder.verify(this.context).setField("client", data.getClient());
		inOrder.verify(this.context).missing();
	}

	private Login buildData() {
		Login data = new Login();
		data.setId(3333);
		data.setName("LOGIN_NAME_A");
		data.setEnabled(true);
		data.setPassword("LOGIN_PWD_A");
		data.setClient(this.buildClient());
		return data;
	}

	private Client buildClient() {
		Client data = new Client();
		data.setId(2222);
		return data;
	}
}