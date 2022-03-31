package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;
import com.bolsadeideas.springboot.backend.apirest.services.ClienteService;

@CrossOrigin(origins = { "*"})
@RestController
@RequestMapping("/Api")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;
 
	@GetMapping("/clientes")
	public ResponseEntity<?> index() {
		Map<String, Object> response = new HashMap<>();
		List<Cliente> clientes = null;
		try {
			clientes = clienteService.findAll();
		} catch (Exception e) {
			response.put("mensaje", "No existen clientes");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Cliente>>(clientes,HttpStatus.OK);
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = null;
		try {
			cliente = clienteService.findById(id);
			
			if (cliente == null) {
				response.put("mensaje", "Cliente no existe");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("mensaje", e.getCause().getCause().getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Cliente clienteNew = null;
		
		if(result.hasErrors()) {
//			List<String> errors = new ArrayList<>();
//			for(FieldError err : result.getFieldErrors()) {
//				errors.add("El Campo " + err.getField() + " " + err.getDefaultMessage());
//			} jdk menos a 8
			
			List<String> errors = result.getFieldErrors().stream().map(err -> { 
				return "El Campo " + err.getField() + " " + err.getDefaultMessage();
				}).collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			clienteNew = clienteService.save(cliente);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cliente>(clienteNew,HttpStatus.CREATED);
	}

	@SuppressWarnings("unused")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente clienteActual = null;
		Cliente clienteSave = null;
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> { 
				return "El Campo " + err.getField() + " " + err.getDefaultMessage();
				}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			clienteActual = clienteService.findById(id);
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			
			if(clienteActual == null) {
				response.put("mensaje", "Cliente no existe");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}else {
				clienteSave = clienteService.save(clienteActual);
				if(clienteSave == null) {
					response.put("mensaje", "Error al grabar");
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
				}
			}
		} catch (Exception e) {
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cliente>(clienteSave,HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.deleteById(id);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente eliminado con exito.");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@DeleteMapping("/clientes")
	public ResponseEntity<?> delete(@RequestBody Cliente cliente) {
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.delete(cliente);
		} catch (Exception e) {
			response.put("mensaje", e.getCause().getCause().getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente eliminado con exito.");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/clientes/deleteAll")
	public ResponseEntity<?> deleteAll() {
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.deleteAll();
		} catch (Exception e) {
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Todos los clientes se eliminaron con exito.");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
