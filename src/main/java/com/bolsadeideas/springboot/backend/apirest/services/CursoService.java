package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.backend.apirest.models.Curso;
import com.bolsadeideas.springboot.backend.apirest.repositories.CursoRepository;

@Service
public class CursoService implements ICursoService{
	
	@Autowired
	private CursoRepository cursoR;

	@Override
	public List<Curso> findAll() {
		return (List<Curso>) cursoR.findAll();
	}

}
