package com.swiftaireng.backend.apirest.models.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swiftaireng.backend.apirest.models.entity.Workday;

@Service
@Transactional
public interface IWorkDayService {

	public List<Workday> findByUserId(Long id);

}
