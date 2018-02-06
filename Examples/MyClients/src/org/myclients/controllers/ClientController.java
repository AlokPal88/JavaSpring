package org.myclients.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.myclients.domain.Client;
import org.myclients.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jeneva.validation.FailResponse;
import org.jeneva.validation.ValidationException;

@Controller
@RequestMapping({"/client"})
public class ClientController {

	@Autowired
	public IClientService clientService;

	@RequestMapping(value={"/list"})
	public String list() {
		return "client/list";
	}

	@RequestMapping("/create")
	public String create() {
		return "client/create";
	}

	@RequestMapping("/edit")
	public String edit() {
		return "client/edit";
	}

	@RequestMapping("/getbyid")
	@ResponseBody
	public Client getById(int id) {
		return this.clientService.getById(id);
	}

	@RequestMapping("/getall")
	@ResponseBody
	public List<Client> getAll() {
		return this.clientService.getAll();
	}

	@RequestMapping("/save")
	@ResponseBody
	public void save(@RequestBody Client item) {
		this.clientService.save(item);
	}

	@RequestMapping("/update")
	@ResponseBody
	public void update(@RequestBody Client item) {
		this.clientService.update(item);
	}

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") int id) {
		this.clientService.delete(id);
	}

	@ExceptionHandler
	@ResponseBody
	public FailResponse handleError(Exception ex, HttpServletResponse response) {
		FailResponse fail = null;
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		if(ex instanceof ValidationException) {
			fail = ((ValidationException) ex).buildFailResponse();
		}
		else {
			fail = new FailResponse(ex.getMessage());
		}

		return fail;
	}
}