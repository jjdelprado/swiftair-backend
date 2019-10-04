package com.swiftaireng.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swiftaireng.backend.apirest.DTO.Mensaje;
import com.swiftaireng.backend.apirest.models.entity.Usuario;
import com.swiftaireng.backend.apirest.models.services.UsuarioService;
import com.swiftaireng.backend.apirest.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class UsuariosRestController {

	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    UsuarioService usuarioService;
	
	@GetMapping(value = "/listar_users")
	public @ResponseBody List<Usuario> listarRest() {
		return usuarioRepository.findAll();
	}
	
	
	@GetMapping("/listar_user_byusername/{username}")
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> showbyusername(@PathVariable String username) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioRepository.findByNombreUsuario(username).orElse(null);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (usuario == null) {
			response.put("mensaje", "El Usuario: ".concat(username.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping("/listar_users/{id}")
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioRepository.findById(id).orElse(null);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (usuario == null) {
			response.put("mensaje", "El Usuario ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PutMapping("/save_password/{id}")
	public ResponseEntity<?> updatePassword (@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
		Usuario usuarioActual = usuarioRepository.findById(id).orElse(null);
		Usuario usuarioUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (usuarioActual == null) {
			response.put("mensaje", "Error: can't edit, user ID: ".concat(id.toString().concat(" don't exist in database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			
			if (usuario.getPassword() != null) {
				usuarioActual.setPassword(passwordEncoder.encode(usuario.getPassword()));
			}
			usuarioUpdated = usuarioRepository.save(usuarioActual);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error to update password of user to database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("usuario" , usuarioUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/save_user/{id}")
	public ResponseEntity<?> update (@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
		Usuario usuarioActual = usuarioRepository.findById(id).orElse(null);
		Usuario usuarioUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (usuarioActual == null) {
			response.put("mensaje", "Error: can't edit, user ID: ".concat(id.toString().concat(" don't exist in database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			usuarioActual.setNombreUsuario(usuario.getNombreUsuario());
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setPrimerApellido(usuario.getPrimerApellido());
			usuarioActual.setSegundoApellido(usuario.getSegundoApellido());
			usuarioActual.setEmail(usuario.getEmail());
			usuarioActual.setRoles(usuario.getRoles());
			usuarioActual.setDepartamentos(usuario.getDepartamentos());
			if (usuario.getPassword() != null) {
				usuarioActual.setPassword(passwordEncoder.encode(usuario.getPassword()));
				
			}
			
			usuarioUpdated = usuarioRepository.save(usuarioActual);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("usuario" , usuarioUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delete_user/{id}", method= {RequestMethod.DELETE})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioRepository.deleteById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido eliminado de la base de datos con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/create_user")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
		
		Usuario usuarioNew = null;
				
		Map<String, Object> response = new HashMap<>();
		 if(result.hasErrors())
	            return new ResponseEntity(new Mensaje("empty fields or invalid email"), HttpStatus.BAD_REQUEST);
	        if(usuarioService.existePorNombre(usuario.getNombreUsuario()))
	            return new ResponseEntity(new Mensaje("this name exist"), HttpStatus.BAD_REQUEST);
	        if(usuarioService.existePorEmail(usuario.getEmail()))
	            return new ResponseEntity(new Mensaje("this email exist"), HttpStatus.BAD_REQUEST);
	        
	        Usuario usuarioActual = new Usuario(usuario.getNombre(),
	        		usuario.getPrimerApellido(),
	        		usuario.getSegundoApellido(),
	        		usuario.getNombreUsuario(),
	        		usuario.getEmail(),
	        		passwordEncoder.encode(usuario.getPassword())
	        		);
			usuarioActual.setRoles(usuario.getRoles());
			usuarioActual.setDepartamentos(usuario.getDepartamentos());
	        if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
//			for(FieldError err: result.getFieldErrors()) {
//				errors.add("El campo '"+err.getField()+"' "+ err.getDefaultMessage());
//			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarioNew = usuarioService.guardar(usuarioActual);
	        //return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
			//usuarioNew = usuarioRepository.save(usuarioActual);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("usuario" , usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
