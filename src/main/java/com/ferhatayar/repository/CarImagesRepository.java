package com.ferhatayar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferhatayar.model.CarImages;

@Repository
public interface CarImagesRepository extends JpaRepository<CarImages, Long>{

	List<CarImages> findByCarId(Long carId);
	
}
