package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.Curso;

public interface ICursoService {
	
	public List<Curso> findAll();

}
