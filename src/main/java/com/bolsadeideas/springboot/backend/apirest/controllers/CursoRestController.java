package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.Curso;
import com.bolsadeideas.springboot.backend.apirest.services.CursoService;

@CrossOrigin(origins = { "http://localhost:4200","http://191.88.101.56:4200","http://192.168.1.16:4200" })
@RequestMapping("/Api2")
@RestController
public class CursoRestController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("/curso")
	public List<Curso> index() {
		return cursoService.findAll();
	}

}
