package com.ferhatayar.service;

import java.util.List;

import com.ferhatayar.dto.DtoCar;
import com.ferhatayar.dto.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU input);
	
	public List<DtoCar> getAllCarList();
	
	public DtoCar getCarById(Long id);
	
	public DtoCar deleteCar(Long id);
	
	public DtoCar updateCar(Long id, DtoCarIU input);
	
}
