package org.myclients.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.myclients.dao.IDaobase;
import org.springframework.core.GenericTypeResolver;

@SuppressWarnings("unchecked")
public class Daobase<T> implements IDaobase<T> {

	protected final Class<T> domainType;
	protected SessionFactory sessionFactory;

	protected Daobase(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.domainType = (Class<T>) GenericTypeResolver.resolveTypeArgument(this.getClass(), Daobase.class);
	}

	@Override
	public void save(T item) {
		this.sessionFactory
			.getCurrentSession()
			.save(item);
	}

	@Override
	public void update(T item) {
		this.sessionFactory
			.getCurrentSession()
			.update(item);
	}

	@Override
	public T merge(T item) {
		return (T)this.sessionFactory
			.getCurrentSession()
			.merge(item);
	}

	@Override
	public void delete(T item) {
		this.sessionFactory
			.getCurrentSession()
			.delete(item);
	}

	@Override
	public T get(Serializable id) {
		return (T)this.sessionFactory
			.getCurrentSession()
			.get(this.domainType, id);
	}

	@Override
	public T load(Serializable id) {
		return (T)this.sessionFactory
			.getCurrentSession()
			.load(this.domainType, id);
	}
}