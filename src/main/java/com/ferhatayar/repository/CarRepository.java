package com.ferhatayar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferhatayar.model.Cars;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long>{

	
}
