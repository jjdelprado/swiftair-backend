package com.swiftaireng.backend.apirest.repository.partcontrol;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.swiftaireng.backend.apirest.models.entity.partcontrol.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	boolean existsByRegistration(String registration);
	boolean existsByFrameNumber(String frameNumber);
	
	@Query(value="SELECT * FROM vehicles v WHERE removal_date is null", nativeQuery = true)
	public List<Vehicle> findVehicleActive();
	
	@Query(value="SELECT v.* FROM vehicles v LEFT JOIN vehicles_use vu ON vu.vehicle_id = v.id WHERE v.removal_date is null AND (vu.return_date is not null OR vu.pickup_date IS NULL) AND v.id NOT IN (SELECT vehicle_id FROM vehicles_use ve WHERE ve.pickup_date is not null and ve.return_date is null) GROUP BY v.id", nativeQuery = true)
	public List<Vehicle> findVehiclesWhithoutUse();

}
