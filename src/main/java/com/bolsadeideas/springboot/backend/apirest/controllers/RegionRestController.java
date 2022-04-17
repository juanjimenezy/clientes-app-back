package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.Region;
import com.bolsadeideas.springboot.backend.apirest.services.RegionService;

@CrossOrigin(origins = { "*"})
@RestController
@RequestMapping("/Api")
public class RegionRestController {
	
	@Autowired
	RegionService regionService;
	
	@GetMapping("/regiones")
	public ResponseEntity<Map<String, Object>> findRegiones() {
		Map<String, Object> response = new HashMap<>();
		List<Region> regiones;
		
		try {
			regiones = regionService.findAllRegiones();
			response.put("Regiones", regiones);
		} catch (Exception e) {
			response.put("Error", e.getCause().getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
