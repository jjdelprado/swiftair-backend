package com.swiftaireng.backend.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swiftaireng.backend.apirest.models.entity.Workday;

@Repository
public interface WorkdayRepository extends JpaRepository<Workday, Long>{

}
