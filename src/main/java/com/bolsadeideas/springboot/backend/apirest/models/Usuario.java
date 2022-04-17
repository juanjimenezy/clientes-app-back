package com.bolsadeideas.springboot.backend.apirest.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="JJ_USUARIOS")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Usuario")
	@SequenceGenerator(name = "id_Usuario", sequenceName = "SQ_JJ_USUARIOS", allocationSize = 1, initialValue = 3)
	private Long id;
	
	@Column(name="USERNAME", unique= true, length = 20)
	private String username;
	
	@Column(name="PASSWORD", length = 60)
	private String password;
	
	@Column(name="ENABLED")
	private Boolean enabled;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="JJ_USUARIOS_ROLES", joinColumns = @JoinColumn(name="usuario_id"), inverseJoinColumns = @JoinColumn(name="role_id"),
			uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"} )})
	private List<Role> roles;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}