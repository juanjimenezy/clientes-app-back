package com.bolsadeideas.springboot.backend.apirest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.models.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {

}
