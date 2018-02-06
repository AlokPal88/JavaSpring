package test.controllers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.myclients.controllers.HomeController;

public class HomeControllerTest {

	private HomeController target;

	@Before
	public void setUp() throws Exception {
		this.target = new HomeController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void indexTest() {
		String actual = this.target.index();
		Assert.assertEquals("home/index", actual);
	}

	@Test
	public void notfoundTest() {
		String actual = this.target.notfound();
		Assert.assertEquals("home/notfound", actual);
	}
}