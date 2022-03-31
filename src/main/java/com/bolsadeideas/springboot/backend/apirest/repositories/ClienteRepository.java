package com.bolsadeideas.springboot.backend.apirest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
}
