package com.swiftaireng.backend.apirest.models.services;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.List;

import com.swiftaireng.backend.apirest.models.entity.LateralMenu;
import com.swiftaireng.backend.apirest.models.entity.TopMenu;

public interface IMenusService {

	public List<LateralMenu> findAll();
	
	public LateralMenu save(LateralMenu cliente);
	
	public void delete(Long id);
	
	public LateralMenu findById(Long id);
	
	public List<TopMenu> fetchIdWithSubMenu(Long id);
}
