package com.ferhatayar.controller;

import java.util.List;

import com.ferhatayar.dto.DtoRental;
import com.ferhatayar.dto.DtoRentalIU;

public interface IRestRentalController {

	public RootEntity<DtoRental> saveRental(DtoRentalIU input);
	
	public RootEntity<List<DtoRental>> getAllRentalList();
	
	public RootEntity<DtoRental> getRentalById(Long id);
	
	public RootEntity<DtoRental> deleteRental(Long id);
	
	public RootEntity<DtoRental> updateRental(Long id, DtoRentalIU input);
	
}
