package com.ferhatayar.controller;

import java.util.List;

import com.ferhatayar.dto.DtoCar;
import com.ferhatayar.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU input);
	
	public RootEntity<List<DtoCar>> getAllCarList();
	
	public RootEntity<DtoCar> getCarById(Long id);
	
	public RootEntity<DtoCar> deleteCar(Long id);
	
	public RootEntity<DtoCar> updateCar(Long id, DtoCarIU input);
	
}
