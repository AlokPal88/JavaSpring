package test.validation.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.myclients.validation.common.IValidationContext;
import org.myclients.validation.common.impl.ValidationContextFactory;


public class ValidationContextFactoryTest {

	private ValidationContextFactory target;

	@Before
	public void setUp() throws Exception {

		this.target = new ValidationContextFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getNewTest() {

		IValidationContext actual1 = this.target.getNew();
		IValidationContext actual2 = this.target.getNew();
		Assert.assertNotNull(actual1);
		Assert.assertNotNull(actual2);
		Assert.assertNotEquals(actual1, actual2);
	}
}