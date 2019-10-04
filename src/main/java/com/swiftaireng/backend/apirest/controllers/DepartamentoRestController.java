package com.swiftaireng.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swiftaireng.backend.apirest.models.entity.Departamento;
import com.swiftaireng.backend.apirest.repository.DepartamentoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class DepartamentoRestController {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@GetMapping(value = "/listar_departamentos")
	public @ResponseBody List<Departamento> listarRest() {
		return departamentoRepository.findAll();
	}
		
}
