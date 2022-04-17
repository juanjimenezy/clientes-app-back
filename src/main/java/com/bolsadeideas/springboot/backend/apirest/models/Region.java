package com.bolsadeideas.springboot.backend.apirest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JJ_REGIONES")
public class Region implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_region")
	@SequenceGenerator(name = "id_sequence_region", sequenceName = "SQ_JJ_REGIONES", allocationSize = 1, initialValue = 3)
	private Long id;

	@Column(name="NOMBRE")
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

	private static final long serialVersionUID = 1L;

}
