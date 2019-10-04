package com.swiftaireng.backend.apirest.models.services;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swiftaireng.backend.apirest.enums.RolNombre;
import com.swiftaireng.backend.apirest.models.entity.Rol;
import com.swiftaireng.backend.apirest.repository.RolRepository;

@Service
@Transactional
public class RolService {
	
	@Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
}
