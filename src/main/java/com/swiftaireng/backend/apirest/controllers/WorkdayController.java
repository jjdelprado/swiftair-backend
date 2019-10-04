package com.swiftaireng.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftaireng.backend.apirest.models.entity.Workday;
import com.swiftaireng.backend.apirest.models.services.IWorkDayService;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
public class WorkdayController {

	@Autowired
	private IWorkDayService iWorkdayService;
	
	@GetMapping("/workday/{id}")
	//@ResponseStatus(HttpStatus.OK)
	public List<Workday> listarWorkday(@PathVariable Long id) {
		return iWorkdayService.findByUserId(id);
	}
	
}
