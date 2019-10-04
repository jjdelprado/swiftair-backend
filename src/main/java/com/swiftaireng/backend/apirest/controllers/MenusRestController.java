package com.swiftaireng.backend.apirest.controllers;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftaireng.backend.apirest.models.entity.LateralMenu;
import com.swiftaireng.backend.apirest.models.entity.TopMenu;
import com.swiftaireng.backend.apirest.models.services.IMenusService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class MenusRestController {

	@Autowired
	private IMenusService menusService;
	
	@GetMapping("/menuLaterales")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public List<LateralMenu> index(){
		return menusService.findAll();
	}
	
	@GetMapping("/menustop/{id}")
	public List<TopMenu> showTopMemu(@PathVariable Long id){
		return menusService.fetchIdWithSubMenu(id);
	}
	
	
	@GetMapping("/menuLaterales/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		LateralMenu lateralMenu = null;
		Map<String, Object> response = new HashMap<>();
		try {
			lateralMenu = menusService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (lateralMenu == null) {
			response.put("mensaje", "El Menu lateral con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LateralMenu>(lateralMenu, HttpStatus.OK);
	}
	
	@PostMapping("/menuLaterales")
	public ResponseEntity<?> create(@Valid @RequestBody LateralMenu lateralMenu, BindingResult result) {
		
		LateralMenu lateralMenuNew = null;
		Map<String, Object> response = new HashMap<>();
		
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
			lateralMenuNew = menusService.save(lateralMenu);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El menu lateral ha sido creado con éxito!");
		response.put("cliente" , lateralMenuNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/menuLaterales/{id}")
	public ResponseEntity<?> update (@Valid @RequestBody LateralMenu lateralMenu, BindingResult result, @PathVariable Long id) {
		LateralMenu lateralMenuActual = menusService.findById(id);
		LateralMenu lateralMenuUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (lateralMenuActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el menú lateral con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
//			lateralMenuActual.setIcono(lateralMenu.getIcono());
			lateralMenuActual.setLink(lateralMenu.getLink());
			lateralMenuUpdated = menusService.save(lateralMenuActual);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el menu lateral en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El menu lateral ha sido actualizado con éxito!");
		response.put("cliente" , lateralMenuUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/menuLaterales/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			menusService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el menu lateral en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El menu lateral ha sido eliminado de la base de datos con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
