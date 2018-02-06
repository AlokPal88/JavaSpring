package org.myclients.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.myclients.dao.IClientDao;
import org.myclients.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao extends Daobase<Client> implements IClientDao {

	@Autowired
	public ClientDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Client getById(int id) {
		return (Client)this.sessionFactory
			.getCurrentSession()
			.createQuery("from Client client left outer join fetch client.logins where client.id = :id")
			.setParameter("id", id)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAll() {
		return this.sessionFactory
			.getCurrentSession()
			.createQuery("from Client client left outer join fetch client.logins")
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
	}

	@Override
	public long getCount()
	{
		return (long)this.sessionFactory
			.getCurrentSession()
			.createQuery("select count(*) from Client")
			.uniqueResult();
	}
}