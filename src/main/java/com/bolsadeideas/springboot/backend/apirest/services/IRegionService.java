package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.Region;

public interface IRegionService {
	
	public List<Region> findAllRegiones();

}
