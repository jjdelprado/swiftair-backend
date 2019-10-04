package com.swiftaireng.backend.apirest.models.dao.partcontrol;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.swiftaireng.backend.apirest.models.entity.partcontrol.Vehicle;

public interface IVehicleDao  extends JpaRepository<Vehicle, Long>{

	@Query("select v from Vehicle v")
	public List<Vehicle> findVehicleWithRevision();
	
}
