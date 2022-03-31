package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;
import com.bolsadeideas.springboot.backend.apirest.repositories.ClienteRepository;

@Service
public class ClienteService implements IClienteService{
	
	@Autowired
	private ClienteRepository clienteR;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteR.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		return clienteR.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public Cliente save(Cliente cliente) {
		return clienteR.save(cliente);
	}

	@Override
	public void deleteById(Long id) {
		clienteR.deleteById(id);
	}

	@Override
	public void delete(Cliente cliente) {
		clienteR.delete(cliente);
	}

	@Override
	public void deleteAll() {
		clienteR.deleteAll();
		
	}

}
