package test.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;
import org.myclients.dao.IClientDao;
import org.myclients.domain.Client;
import org.myclients.domain.Levels;
import org.myclients.services.impl.ClientService;
import org.myclients.validation.common.IValidationFacade;
import org.jeneva.IMapper;
import org.jeneva.validation.IFailureList;

public class ClientServiceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	private IValidationFacade validator;
	private IMapper mapper;
	private IClientDao dao;
	private IFailureList failures;
	private ClientService target;

	@Before
	public void setUp() throws Exception {
		this.validator = mock(IValidationFacade.class);
		this.mapper = mock(IMapper.class);
		this.dao = mock(IClientDao.class);
		this.failures = mock(IFailureList.class);
		this.target = new ClientService();
		this.target.clientDao = this.dao;
		this.target.mapper = this.mapper;
		this.target.validator = this.validator;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getByIdTest() {
		Client daoObject = new Client();
		daoObject.setId(2422);
		Client filteredObject = new Client();
		filteredObject.setId(3452);
		when(this.dao.getById(daoObject.getId())).thenReturn(daoObject);
		when(this.mapper.filter(daoObject, Client.class, Levels.DEEP)).thenReturn(filteredObject);
		Client actual = this.target.getById(daoObject.getId());
		Assert.assertEquals(filteredObject, actual);
	}

	@Test
	public void getAllTest() {
		List<Client> daoList = new ArrayList<Client>();
		List<Client> filteredList = new ArrayList<Client>();
		when(this.dao.getAll()).thenReturn(daoList);
		when(this.mapper.filterList(daoList, Client.class, Levels.GRID)).thenReturn(filteredList);
		List<Client> actual = this.target.getAll();
		Assert.assertEquals(filteredList, actual);
	}

	@Test
	public void saveTest() {
		Client input = new Client();
		input.setId(2422);
		this.target.save(input);
		verify(this.validator).assertClientForSave(input);
		verify(this.mapper).map(input, Client.class);
		verify(this.dao).save(input);
	}

	@Test
	public void updateTest() {
		Client input = new Client();
		input.setId(2422);
		Client existing = new Client();
		existing.setId(4355);
		when(this.dao.load(input.getId())).thenReturn(existing);
		this.target.update(input);
		verify(this.validator).assertClientForUpdate(input);
		verify(this.mapper).mapTo(input, existing, Client.class);
		verify(this.dao).merge(existing);
	}

	@Test
	public void deleteTest()
	{
		int input = 4235;
		long count = 3;
		Client existing = new Client();
		existing.setId(6786);

		when(this.dao.getById(input)).thenReturn(existing);
		when(this.dao.getCount()).thenReturn(count);

		this.target.delete(input);

		InOrder inOrder = inOrder(this.validator, this.dao, this.failures);
		inOrder.verify(this.validator).assertClientExists(existing);
		inOrder.verify(this.validator).assertMoreThanOneClient(count);
		inOrder.verify(this.dao).delete(existing);
	}
}