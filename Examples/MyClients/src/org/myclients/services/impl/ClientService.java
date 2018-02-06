package org.myclients.services.impl;

import java.util.List;

import org.myclients.dao.IClientDao;
import org.myclients.domain.Client;
import org.myclients.domain.Levels;
import org.myclients.services.IClientService;
import org.myclients.validation.common.IValidationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jeneva.IMapper;

@Service
public class ClientService implements IClientService {

	@Autowired
	public IMapper mapper;

	@Autowired
	public IValidationFacade validator;

	@Autowired
	public IClientDao clientDao;


	@Override
	public Client getById(int clientId) {
		Client item = this.clientDao.getById(clientId);
		return this.mapper.filter(item, Client.class, Levels.DEEP);
	}

	@Override
	public List<Client> getAll() {
		List<Client> items = this.clientDao.getAll();
		return this.mapper.filterList(items, Client.class, Levels.GRID);
	}

	@Override
	public void save(Client item) {
		this.validator.assertClientForSave(item);
		this.mapper.map(item, Client.class);
		this.clientDao.save(item);
	}

	@Override
	public void update(Client item) {
		this.validator.assertClientForUpdate(item);
		Client existing = this.clientDao.load(item.getId());
		this.mapper.mapTo(item, existing, Client.class);
		this.clientDao.merge(existing);
	}

	@Override
	public void delete(int id) {
		Client existing = this.clientDao.getById(id);
		this.validator.assertClientExists(existing);
		long count = this.clientDao.getCount();
		this.validator.assertMoreThanOneClient(count);
		this.clientDao.delete(existing);
	}
}