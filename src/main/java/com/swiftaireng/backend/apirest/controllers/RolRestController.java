package com.swiftaireng.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swiftaireng.backend.apirest.models.entity.Rol;
import com.swiftaireng.backend.apirest.repository.RolRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class RolRestController {

	@Autowired
	private RolRepository rolRepository;
	
	@GetMapping(value = "/listar_roles")
	public @ResponseBody List<Rol> listarRest() {
		return rolRepository.findAll();
	}
		
}
