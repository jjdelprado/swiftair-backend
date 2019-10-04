package com.swiftaireng.backend.apirest.models.dao;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import org.springframework.data.repository.CrudRepository;

import com.swiftaireng.backend.apirest.models.entity.SubMenu;

public interface ISubMenuMenuDao  extends CrudRepository<SubMenu, Long>{

}
