package org.myclients.dao;

import java.io.Serializable;

public interface IDaobase<T> {
	void save(T item);
	void update(T item);
	T merge(T item);
	void delete(T item);
	T get(Serializable id);
	T load(Serializable id);
}