package com.swiftaireng.backend.apirest.repository.partcontrol;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swiftaireng.backend.apirest.models.entity.partcontrol.VehicleUse;
import com.swiftaireng.backend.apirest.models.entity.partcontrol.Vehicle;

@Repository
public interface VehicleUseRepository extends JpaRepository<VehicleUse, Long> {

//	boolean existsByMatricula(String matricula);
//	boolean existsByIdNumber(String idNumber);
//	
//	@Query(value="SELECT * FROM vehiculos v WHERE fecha_baja is null", nativeQuery = true)
//	public List<Vehiculo> findVehicleActive();
//	
//	@Query(value="SELECT v.* FROM vehiculos v LEFT JOIN vehicles_use vu ON vu.vehicle_id = v.id WHERE v.fecha_baja is null AND (vu.return_date is not null OR vu.pickup_date IS NULL)", nativeQuery = true)
//	public List<Vehiculo> findVehiclesWhithoutUse();
//	
	@Query(value="SELECT v FROM VehicleUse v WHERE v.pickupDate BETWEEN CONCAT(?1,' 00:00:00') AND CONCAT(?1,' 23:59:59')")
	public List<VehicleUse> findVehicleUse(String date);
	
	@Query(value="SELECT v FROM VehicleUse v WHERE v.returnDate is null")
	public List<VehicleUse> listVehicleUsenotReturned();

}
