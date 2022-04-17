package com.bolsadeideas.springboot.backend.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
	
	
	public Usuario findByUsername(String username);
	
//	@Query("SELECT u Usuario u WHERE u.username = :username")
//	public Usuario findByUsername2(@Param(value = "username") String username);

}
