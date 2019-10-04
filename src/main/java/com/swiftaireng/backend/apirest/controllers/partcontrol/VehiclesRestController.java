package com.swiftaireng.backend.apirest.controllers.partcontrol;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.swiftaireng.backend.apirest.models.entity.partcontrol.Vehicle;
import com.swiftaireng.backend.apirest.models.entity.partcontrol.VehicleUse;
import com.swiftaireng.backend.apirest.repository.partcontrol.VehicleRepository;
import com.swiftaireng.backend.apirest.repository.partcontrol.VehicleUseRepository;

@RestController
@RequestMapping("/api/vehiculo")
@CrossOrigin(origins = { "http://localhost:4200" })
public class VehiclesRestController {

	@Autowired
	private VehicleRepository vehiculoRepository;

	@Autowired
	private VehicleUseRepository vehicleUseRepository;
	
	// FUNCTIONS TO GET VEHICLES AND VEHICLES USE

	@GetMapping(value = "/listarVehicles")
	public @ResponseBody List<Vehicle> show() {
		// Funcion para listar todos los vehículos, estén o no dados de alta
		return vehiculoRepository.findAll();
	}

	@GetMapping("/listar/{id}")
	// @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> showById(@PathVariable Long id) {
		// Function to get vehicle in base of id
		Vehicle vehiculo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			vehiculo = vehiculoRepository.findById(id).orElse(null);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to access database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (vehiculo == null) {
			response.put("mensaje", "The vehicle ID: ".concat(id.toString().concat(" not exist in database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vehicle>(vehiculo, HttpStatus.OK);
	}

	@GetMapping(value = "/listarActive")
	public @ResponseBody List<Vehicle> ListVehicleActive() {
		// Funcion para listar los vehículos que no tienen fecha de baja, es decir, que
		// están activos
		return vehiculoRepository.findVehicleActive();
	}

	@GetMapping(value = "/listarSinUso")
	public @ResponseBody List<Vehicle> ListVehicleWithoutUse() {
		// Funcion para listar los vehículos que no tienen fecha de baja y además no
		// están entregados a conductores
		return vehiculoRepository.findVehiclesWhithoutUse();
	}

	@PostMapping(value = "/listVehUse")
	public ResponseEntity<?> ListVehicleForDate(@RequestBody String date) {
		// Function to get a list of vehicles Use in base of a date
		List<VehicleUse> vehicleUse;
		Map<String, Object> response = new HashMap<>();
		try {
			vehicleUse = vehicleUseRepository.findVehicleUse(date);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to access database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (vehicleUse.isEmpty()) {
			response.put("mensaje", "There isn't vehicles in database in that date");
			response.put("objsLenght", "0");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<List<VehicleUse>>(vehicleUse, HttpStatus.OK);
	}

	@GetMapping(value = "/listVehicleUsenotReturned")
	public ResponseEntity<?> listVehicleUsenotReturned() {
		// Function to get a list of vehicles Use what vehicle has not been returned
		List<VehicleUse> vehicleUse;
		Map<String, Object> response = new HashMap<>();
		try {
			vehicleUse = vehicleUseRepository.listVehicleUsenotReturned();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to access database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (vehicleUse.isEmpty()) {
			response.put("mensaje", "There isn't vehicles in database");
			response.put("objsLenght", "0");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<List<VehicleUse>>(vehicleUse, HttpStatus.OK);
	}
	
	
	@GetMapping("/listVehUse/{id}")
	// @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> showVehUseById(@PathVariable Long id) {
		// Function to get a vehicle Use in base of id
		VehicleUse vehicleUse = null;
		Map<String, Object> response = new HashMap<>();
		try {
			vehicleUse = vehicleUseRepository.findById(id).orElse(null);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to access database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (vehicleUse == null) {
			response.put("mensaje", "The vehicle use ID: ".concat(id.toString().concat(" not exist in database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<VehicleUse>(vehicleUse, HttpStatus.OK);
	}
	
	@PutMapping("/returnVehUse/{id}")
	public ResponseEntity<?> returnVehicleUse(@RequestBody VehicleUse vehicleUse, @PathVariable Long id) {
		// Function to return a Vehicle Use in base of id
		VehicleUse vehicleUseActual = vehicleUseRepository.findById(id).orElse(null);
		VehicleUse vehicleUseUpdated = null;
		Date returnDate = new Date();
		System.out.println(returnDate);
		Map<String, Object> response = new HashMap<>();

		if (vehicleUseActual == null) {
			response.put("mensaje",
					"Error: can't edit, vehicle use ID: ".concat(id.toString().concat(" don't exist in database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {

			vehicleUseActual.setReturnDate(returnDate);
//			if (vehicleUse.getAuxMechSystem() != null) {
//				vehicleUseActual.setAuxMechSystem(vehicleUse.getAuxMechSystem());
//			}
//			if (vehicleUse.getIncidents() != null) {
//				vehicleUseActual.setIncidents(vehicleUse.getIncidents());
//			}
//			if (vehicleUse.getInnerDefect() != null) {
//				vehicleUseActual.setInnerDefect(vehicleUse.getInnerDefect());
//			}
//			if (vehicleUse.getSignature() != null) {
//				vehicleUseActual.setSignature(vehicleUse.getSignature());
//			}
//			if (vehicleUse.getSignificantBlow() != null) {
//				vehicleUseActual.setSignificantBlow(vehicleUse.getSignificantBlow());
//			}
//			if (vehicleUse.getSpillOrder() != null) {
//				vehicleUseActual.setSpillOrder(vehicleUse.getSpillOrder());
//			}
			if (vehicleUse.getUserCheckout() != null) {
				vehicleUseActual.setUserCheckout(vehicleUse.getUserCheckout());
			}

			vehicleUseUpdated = vehicleUseRepository.save(vehicleUseActual);
			System.out.println(vehicleUseUpdated.getReturnDate());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to update cehicle Use in database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Vehicle Use has succesfully returned!");
		response.put("vehicleUse", vehicleUseUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// ACTIONS FROM VEHICLES USE

	@PostMapping("/createVehUse")
	public ResponseEntity<?> createVehUse(@Valid @RequestBody VehicleUse vehicleUse, BindingResult result) {
		// Function to create a Vehicle Use
		Map<String, Object> response = new HashMap<>();
		VehicleUse vehiculoUseActual;
		Date pickupDate = new Date();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> err.getDefaultMessage() + ',' + err.getField()).collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		VehicleUse vehiculoUseNew = new VehicleUse(vehicleUse.getVehiculo(), vehicleUse.getService(), pickupDate,
				vehicleUse.getDriverName(), vehicleUse.getEstimatedUse(), vehicleUse.getShift(),
				vehicleUse.getShiftChief(), vehicleUse.getTask(), vehicleUse.getUserCheckin());

		try {
			vehiculoUseActual = vehicleUseRepository.save(vehiculoUseNew);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Vehicle use has been created!");
		response.put("usuario", vehiculoUseActual);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/updateVehUse/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody VehicleUse vehicleUse, BindingResult result,
			@PathVariable Long id) {
		// Function to update a Vehicle Use
		VehicleUse vehicleUseActual = vehicleUseRepository.findById(id).orElse(null);
		VehicleUse vehicleUseUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> err.getDefaultMessage() + ',' + err.getField()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (vehicleUseActual == null) {
			response.put("mensaje",
					"Error: can't edit, vehicle use ID: ".concat(id.toString().concat(" don't exist in database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			vehicleUseActual.setDriverName(vehicleUse.getDriverName());
			vehicleUseActual.setService(vehicleUse.getService());
			vehicleUseActual.setEstimatedUse(vehicleUse.getEstimatedUse());
			vehicleUseActual.setShift(vehicleUse.getShift());
			vehicleUseActual.setShiftChief(vehicleUse.getShiftChief());
			vehicleUseActual.setTask(vehicleUse.getTask());

			vehicleUseUpdated = vehicleUseRepository.save(vehicleUseActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to update cehicle Use in database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Vehicle Use has been updated!");
		response.put("vehicleUse", vehicleUseUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deleteVehUse/{id}", method = { RequestMethod.DELETE })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		// Function to remove Vehicle Use from database
		Map<String, Object> response = new HashMap<>();
		try {
			vehicleUseRepository.deleteById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to remove registry from database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Use Vehicle has been succesfully removed from database!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// ACTIONS FROM VEHICLES

	@PostMapping("/createVehicle")
	public ResponseEntity<?> create(@Valid @RequestBody Vehicle vehiculo, BindingResult result) {
		// Function to create a Vehicle
		Vehicle vehiculoNew = null;

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors())
			return new ResponseEntity(new Mensaje("empty fields"), HttpStatus.BAD_REQUEST);
		String Mensaje = "";
		Boolean Reg=false;
		List<String> errores = new ArrayList<String>();
		if (vehiculoRepository.existsByRegistration(vehiculo.getRegistration())) {
			Mensaje = "registration";
			errores.add("registration");
			Reg=true;
		}
		if (vehiculoRepository.existsByFrameNumber(vehiculo.getFrameNumber())) {
			if(Reg) {
				Mensaje = Mensaje + " and frame number have";
			}else {
				Mensaje = Mensaje + "Frame number has";
			}
			errores.add("frameNumber");
		}else {
			if(!errores.isEmpty()) {
				Mensaje = Mensaje + " has";
			}
		}
		if(!errores.isEmpty()) {
			Mensaje = Mensaje + " duplicate registry";
			response.put("mensaje", Mensaje);
			response.put("errors", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}		
		
		Date fechaAlta = new Date();
		Vehicle vehiculoActual = new Vehicle(vehiculo.getRegistration(), vehiculo.getBrand(), vehiculo.getModel(),
				vehiculo.getFrameNumber(), vehiculo.getYear(), vehiculo.getFuel(), fechaAlta, vehiculo.getRemovalDate(),
				vehiculo.getUserModify());

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			vehiculoNew = vehiculoRepository.save(vehiculoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to insert vehicle in database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Vehicle has been created!");
		response.put("usuario", vehiculoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/updateVehicle/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Vehicle vehiculo, BindingResult result, @PathVariable Long id) {
		// Function to update a Vehicle
		Vehicle vehiculoActual = vehiculoRepository.findById(id).orElse(null);
		Vehicle vehiculoUpdated = null;
		Map<String, Object> response = new HashMap<>();
	
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (vehiculoActual == null) {
			response.put("mensaje",
					"Error: can't edit, vehicle ID: ".concat(id.toString().concat(" don't exist in database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
		Date updateDate = new Date();
		
		try {
			vehiculoActual.setRegistration(vehiculo.getRegistration());
			vehiculoActual.setBrand(vehiculo.getBrand());
			vehiculoActual.setModel(vehiculo.getModel());
			vehiculoActual.setFrameNumber(vehiculo.getFrameNumber());
			vehiculoActual.setYear(vehiculo.getYear());
			vehiculoActual.setFuel(vehiculo.getFuel());
			vehiculoActual.setUpdateDate(updateDate);
			vehiculoActual.setUserModify(vehiculo.getUserModify());

			vehiculoUpdated = vehiculoRepository.save(vehiculoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to update vehicle in database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Vehicle has been updated!");
		response.put("vehiculo", vehiculoUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/unsubscribeVehicle/{id}")
	public ResponseEntity<?> unsubscribeVehicle(@Valid @RequestBody Vehicle vehiculo, BindingResult result, @PathVariable Long id) {
		// Function to unsubscribe a Vehicle
		Vehicle vehiculoActual = vehiculoRepository.findById(id).orElse(null);
		Vehicle vehiculoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		Date removalDate = new Date();
		
		try {
			vehiculoActual.setRemovalDate(removalDate);
			vehiculoActual.setUserModify(vehiculo.getUserModify());

			vehiculoUpdated = vehiculoRepository.save(vehiculoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to removal vehicle in database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Vehicle has been removal!");
		response.put("vehiculo", vehiculoUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/subscribeVehicle/{id}")
	public ResponseEntity<?> subscribeVehicle(@Valid @RequestBody Vehicle vehiculo, BindingResult result, @PathVariable Long id) {
		// Function to unsubscribe a Vehicle
		Vehicle vehiculoActual = vehiculoRepository.findById(id).orElse(null);
		Vehicle vehiculoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		Date removalDate = null;
		
		try {
			vehiculoActual.setRemovalDate(removalDate);
			vehiculoActual.setUserModify(vehiculo.getUserModify());

			vehiculoUpdated = vehiculoRepository.save(vehiculoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to removal vehicle in database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Vehicle has been removal!");
		response.put("vehiculo", vehiculoUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteVehicle/{id}", method = { RequestMethod.DELETE })
	public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
		// Function to remove Vehicle from database
		Map<String, Object> response = new HashMap<>();
		try {
			vehiculoRepository.deleteById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to remove vehicle from database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Vehicle has been succesfully removed from database!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
