package com.swiftaireng.backend.apirest.models.services.impl;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swiftaireng.backend.apirest.models.dao.ILateralMenuDao;
import com.swiftaireng.backend.apirest.models.dao.ITopMenuDao;
import com.swiftaireng.backend.apirest.models.entity.LateralMenu;
import com.swiftaireng.backend.apirest.models.entity.TopMenu;
import com.swiftaireng.backend.apirest.models.services.IMenusService;


@Service
public class MenusServiceImpl implements IMenusService{

	@Autowired
	private ITopMenuDao topmenuDao;
	
	@Autowired
	private ILateralMenuDao lateralMenuDao;

	@Override
	@Transactional(readOnly=true)
	public List<LateralMenu> findAll() {
		return lateralMenuDao.findAllByOrderByDepartamentoNombreAsc();
		//return (List<LateralMenu>) lateralMenuDao.findAll();
	}

	@Override
	@Transactional
	public LateralMenu save(LateralMenu cliente) {
		return lateralMenuDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		lateralMenuDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public LateralMenu findById(Long id) {
		return lateralMenuDao.findById(id).orElse(null);
	}

	@Override
	public List<TopMenu> fetchIdWithSubMenu(Long id) {
		// TODO Auto-generated method stub
		return topmenuDao.fetchIdWithSubMenu(id);
	}

}
