package test.dao;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.myclients.dao.IClientDao;
import org.myclients.dao.impl.ClientDao;
import org.myclients.domain.Client;

public class ClientDaoTest {

	private IClientDao target;
	private SessionFactory sessionFactory;
	private Session session;
	private Query query;

	@Before
	public void setUp() throws Exception {
		this.query = mock(Query.class);
		this.session = mock(Session.class);
		this.sessionFactory = mock(SessionFactory.class);
		this.target = new ClientDao(this.sessionFactory);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getByIdTest() {
		Client expected = new Client();
		expected.setId(1111);
		when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		when(this.session.createQuery("from Client client left outer join fetch client.logins where client.id = :id")).thenReturn(this.query);
		when(this.query.setParameter("id", 1111)).thenReturn(this.query);
		when(this.query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(this.query);
		when(this.query.uniqueResult()).thenReturn(expected);

		Client actual = this.target.getById(expected.getId());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getAllTest() {
		List<Client> expected = new ArrayList<Client>();
		when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		when(this.session.createQuery("from Client client left outer join fetch client.logins")).thenReturn(this.query);
		when(this.query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(this.query);
		when(this.query.list()).thenReturn(expected);

		List<Client> actual = this.target.getAll();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getCountTest()
	{
		long expected = 20;
		when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		when(this.session.createQuery("select count(*) from Client")).thenReturn(this.query);
		when(this.query.uniqueResult()).thenReturn(expected);

		long actual = this.target.getCount();
		Assert.assertEquals(expected, actual);
	}
}