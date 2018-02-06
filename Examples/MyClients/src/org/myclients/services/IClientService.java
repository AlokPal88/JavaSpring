package org.myclients.services;

import java.util.List;

import org.myclients.domain.Client;
import org.springframework.transaction.annotation.Transactional;

public interface IClientService {

	@Transactional(readOnly=true)
	Client getById(int clientId);

	@Transactional(readOnly=true)
	List<Client> getAll();

	@Transactional
	void save(Client item);

	@Transactional
	void update(Client item);

	@Transactional
	void delete(int id);
}