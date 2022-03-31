package com.bolsadeideas.springboot.backend.apirest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JJ_CURSOS")
public class Curso {
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOMBRE", nullable=false)
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
