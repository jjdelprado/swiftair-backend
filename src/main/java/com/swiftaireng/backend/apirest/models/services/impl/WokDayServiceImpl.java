package com.swiftaireng.backend.apirest.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftaireng.backend.apirest.models.dao.IWorkdayDao;
import com.swiftaireng.backend.apirest.models.entity.Workday;
import com.swiftaireng.backend.apirest.models.services.IWorkDayService;

@Service
public class WokDayServiceImpl implements IWorkDayService{

	@Autowired
	private IWorkdayDao workdayDao;
	
	@Override
	public List<Workday> findByUserId(Long id) {
		// TODO Auto-generated method stub
		
		return workdayDao.fetchIdWithUsuario(id);
	}

}
