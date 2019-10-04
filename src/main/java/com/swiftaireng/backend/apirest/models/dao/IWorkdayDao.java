package com.swiftaireng.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.swiftaireng.backend.apirest.models.entity.Workday;

public interface IWorkdayDao extends CrudRepository<Workday, Long>{

	@Query("select w from Workday w join fetch w.usuario u where u.id=?1")
	public List<Workday> fetchIdWithUsuario(Long id);
	
}
