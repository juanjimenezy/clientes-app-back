package com.bolsadeideas.springboot.backend.apirest.services;

import com.bolsadeideas.springboot.backend.apirest.models.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
