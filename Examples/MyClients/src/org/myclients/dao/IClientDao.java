package org.myclients.dao;

import java.util.List;

import org.myclients.domain.Client;

public interface IClientDao extends IDaobase<Client> {
	Client getById(int id);
	List<Client> getAll();
	long getCount();
}