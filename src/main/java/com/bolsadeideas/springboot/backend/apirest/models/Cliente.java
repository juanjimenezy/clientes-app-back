package com.bolsadeideas.springboot.backend.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="JJ_CLIENTES")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "SQ_JJ_CLIENTES", allocationSize = 1, initialValue = 28)
	@Column(name="ID")
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 4, max = 20)
	@Column(name="NOMBRE", nullable=false)
	private String nombre;
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(name="APELLIDO", nullable=false)
	private String apellido;
	
	@NotEmpty(message = "No puede estar vacio")
	@Email(message="no es una direccion de correo bien estructuradaaa!")
	@Column(name="EMAIL", nullable=false, unique=true)
	private String email;
	
//	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name="CREATE_AT")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Column(name="FOTO")
	private String foto;
	
//	@PrePersist
//	public void prePersist() {
//		this.createAt = new Date();
//	}

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
