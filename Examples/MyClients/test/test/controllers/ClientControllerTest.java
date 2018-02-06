package test.controllers;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.myclients.controllers.ClientController;
import org.myclients.domain.Client;
import org.myclients.services.IClientService;
import org.jeneva.validation.FailResponse;
import org.jeneva.validation.IFailureList;
import org.jeneva.validation.Severity;
import org.jeneva.validation.ValidationException;

public class ClientControllerTest {

	private IClientService clientService;
	private ClientController target;
	private HttpServletResponse response;
	private IFailureList failures;
	private FailResponse failResponse;

	@Before
	public void setUp() throws Exception {
		this.clientService = mock(IClientService.class);
		this.response = mock(HttpServletResponse.class);
		this.failures = mock(IFailureList.class);
		this.failResponse = mock(FailResponse.class);
		this.target = new ClientController();
		this.target.clientService = this.clientService;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void listTest() {
		String actual = this.target.list();
		Assert.assertEquals("client/list", actual);
	}

	@Test
	public void createTest() {
		String actual = this.target.create();
		Assert.assertEquals("client/create", actual);
	}

	@Test
	public void editTest() {
		String actual = this.target.edit();
		Assert.assertEquals("client/edit", actual);
	}

	@Test
	public void getByIdTest() {
		int input = 123;
		Client expected = new Client();
		expected.setId(input);
		when(this.clientService.getById(input)).thenReturn(expected);
		Client actual = this.target.getById(input);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getAllTest() {
		List<Client> expected = new ArrayList<Client>();
		when(this.clientService.getAll()).thenReturn(expected);
		List<Client> actual = this.target.getAll();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void saveTest() {
		Client input = new Client();
		input.setId(123);
		this.target.save(input);
		verify(this.clientService).save(input);
	}

	@Test
	public void updateTest() {
		Client input = new Client();
		input.setId(123);
		this.target.update(input);
		verify(this.clientService).update(input);
	}

	@Test
	public void deleteTest() {
		int input = 12345;
		this.target.delete(input);
		verify(this.clientService).delete(input);
	}

	@Test
	public void handleError_Validation_None_Test() {
		ValidationException exc = mock(ValidationException.class);
		when(exc.buildFailResponse()).thenReturn(this.failResponse);
		when(this.failResponse.getFailures()).thenReturn(this.failures);
		when(this.failures.getSeverity()).thenReturn(Severity.None);
		FailResponse actual = this.target.handleError(exc, this.response);

		verify(this.response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
		Assert.assertEquals(this.failResponse, actual);
	}


	@Test
	public void handleError_Other_Test() {
		String errorMessage = "Random Text";
		FailResponse expected = new FailResponse(errorMessage);
		Exception exc = mock(Exception.class);
		when(exc.getMessage()).thenReturn(errorMessage);
		FailResponse actual = this.target.handleError(exc, this.response);

		verify(this.response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
		Assert.assertEquals(1, actual.getFailures().size());
		Assert.assertEquals(
				expected.getFailures().get(0).getText(),
				actual.getFailures().get(0).getText());
	}
}