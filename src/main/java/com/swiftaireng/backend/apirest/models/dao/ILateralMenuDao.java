package com.swiftaireng.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import org.springframework.data.repository.CrudRepository;

import com.swiftaireng.backend.apirest.models.entity.LateralMenu;

public interface ILateralMenuDao  extends JpaRepository<LateralMenu, Long>{
	
	public List<LateralMenu> findAllByOrderByDepartamentoNombreAsc();

}
