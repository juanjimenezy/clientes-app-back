package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;
import com.bolsadeideas.springboot.backend.apirest.services.ClienteService;
import com.bolsadeideas.springboot.backend.apirest.services.UploadFileServiceImpl;

@CrossOrigin(origins = { "*"})
@RestController
@RequestMapping("/Api")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UploadFileServiceImpl uploadService;
	
//	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
 
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
	
	@GetMapping("/clientes/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Map<String, Object> response = new HashMap<>();
		Page<Cliente> clientes;
		try {
			clientes = clienteService.findAll(PageRequest.of(page, 4));
		} catch (Exception e) {
			response.put("mensaje", "No existen clientes");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Page<Cliente>>(clientes,HttpStatus.OK);
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
			clienteActual.setRegion(cliente.getRegion());
			
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
			Cliente cliente = clienteService.findById(id);
			String nombreFotoAnterior = cliente.getFoto();
			uploadService.eliminar(nombreFotoAnterior);
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
			
			String nombreFotoAnterior = cliente.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
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
	
	
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = clienteService.findById(id);
		if(!archivo.isEmpty()){
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje","Error al subir imagen del cliente");
				response.put("mensaje", e.getCause().getMessage());
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = cliente.getFoto();
			uploadService.eliminar(nombreFotoAnterior);
			
			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);
			response.put("cliente", cliente);
			response.put("mensaje", "Has cargado correctamente la imagen: " + nombreArchivo);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (Exception e) {
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

}
