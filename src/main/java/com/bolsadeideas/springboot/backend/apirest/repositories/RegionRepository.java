package com.bolsadeideas.springboot.backend.apirest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long>{
	
	@Query("FROM Region")
	public List<Region> findAllRegiones();
}
