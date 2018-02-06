package test.validation;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.myclients.domain.Client;
import org.myclients.domain.Login;
import org.myclients.validation.ILoginValidator;
import org.myclients.validation.common.IValidationContext;
import org.myclients.validation.impl.ClientValidator;
import org.jeneva.ListAndSet;

public class ClientValidatorTest {

	private IValidationContext context;
	private ILoginValidator loginValidator;
	private ClientValidator target;
	private Login loginA;
	private Login loginB;

	@Before
	public void setUp() throws Exception {

		this.context = mock(IValidationContext.class);
		this.loginValidator = mock(ILoginValidator.class);
		this.target = new ClientValidator();
		this.target.loginValidator = this.loginValidator;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validateForSaveTest() {

		Client data = this.buildData();
		this.target.validateForSave(data, this.context);
		InOrder inOrder = inOrder(this.context, this.loginValidator);

		inOrder.verify(this.context).setField("id", data.getId());
		inOrder.verify(this.context).missing();
		
		inOrder.verify(this.context).setField("name", data.getName());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();
		
		inOrder.verify(this.context).setField("lastname", data.getLastname());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();
		
		inOrder.verify(this.context).setField("age", data.getAge());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).number();
		inOrder.verify(this.context).greaterThan(0, "must be greater than zero");
		
		inOrder.verify(this.context).setField("logins", data.getLogins());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).countIsBetween(1, 5, "must be at least one login");
		
		inOrder.verify(this.context).addNested();
		inOrder.verify(this.context).setIndex(0);
		inOrder.verify(this.context).setTarget(this.loginA);
		inOrder.verify(this.loginValidator).validateForSave(this.loginA, this.context);
		
		inOrder.verify(this.context).setIndex(1);
		inOrder.verify(this.context).setTarget(this.loginB);
		inOrder.verify(this.loginValidator).validateForSave(this.loginB, this.context);
		inOrder.verify(this.context).removeNested();
	}

	@Test
	public void validateForUpdateTest() {

		Client data = this.buildData();
		this.target.validateForUpdate(data, this.context);
		InOrder inOrder = inOrder(this.context, this.loginValidator);
		
		inOrder.verify(this.context).setField("id", data.getId());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).number();
		
		inOrder.verify(this.context).setField("name", data.getName());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();
		
		inOrder.verify(this.context).setField("lastname", data.getLastname());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).text();
		
		inOrder.verify(this.context).setField("age", data.getAge());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).number();
		inOrder.verify(this.context).greaterThan(0, "must be greater than zero");
		
		inOrder.verify(this.context).setField("logins", data.getLogins());
		inOrder.verify(this.context).required();
		inOrder.verify(this.context).countIsBetween(1, 5, "must be at least one login");
		
		inOrder.verify(this.context).addNested();
		inOrder.verify(this.context).setIndex(0);
		inOrder.verify(this.context).setTarget(this.loginA);
		inOrder.verify(this.loginValidator).validateForMerge(this.loginA, this.context);
		
		inOrder.verify(this.context).setIndex(1);
		inOrder.verify(this.context).setTarget(this.loginB);
		inOrder.verify(this.loginValidator).validateForMerge(this.loginB, this.context);
		inOrder.verify(this.context).removeNested();
	}

	private Client buildData() {

		Client clientA = new Client(); clientA.setId(1111);
		Client clientB = new Client(); clientB.setId(2222);
		Login a = new Login();
		a.setId(3333);
		a.setName("LOGIN_NAME_A");
		a.setEnabled(true);
		a.setPassword("LOGIN_PWD_A");
		a.setClient(clientA);
		this.loginA = a;

		Login b = new Login();
		b.setId(4444);
		b.setName("LOGIN_NAME_B");
		b.setEnabled(false);
		b.setPassword("LOGIN_PWD_B");
		b.setClient(clientB);
		this.loginB = b;

		ListAndSet<Login> logins = new ListAndSet<>();
		logins.add(a);
		logins.add(b);

		Client data = new Client();
		data.setId(1111);
		data.setAge(2222);
		data.setName("NAME");
		data.setLastname("LASTNAME");
		data.setLogins(logins);
		return data;
	}
}