package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.backend.apirest.models.Region;
import com.bolsadeideas.springboot.backend.apirest.repositories.RegionRepository;

@Service
public class RegionService implements IRegionService{
	
	@Autowired
	private RegionRepository regionRepository;

	@Override
	public List<Region> findAllRegiones() {
		return regionRepository.findAllRegiones();
	}

}
