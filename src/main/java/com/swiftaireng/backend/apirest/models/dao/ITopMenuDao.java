package com.swiftaireng.backend.apirest.models.dao;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.swiftaireng.backend.apirest.models.entity.TopMenu;

public interface ITopMenuDao  extends CrudRepository<TopMenu, Long>{

	@Query("select t from TopMenu t join fetch t.departamento d where d.id=?1")
	public List<TopMenu> fetchIdWithSubMenu(Long id);
	
}
