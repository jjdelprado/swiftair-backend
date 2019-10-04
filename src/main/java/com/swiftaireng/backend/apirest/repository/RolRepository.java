package com.swiftaireng.backend.apirest.repository;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swiftaireng.backend.apirest.enums.RolNombre;
import com.swiftaireng.backend.apirest.models.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
