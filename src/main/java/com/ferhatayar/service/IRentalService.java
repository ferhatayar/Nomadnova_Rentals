package com.ferhatayar.service;

import java.util.List;

import com.ferhatayar.dto.DtoRental;
import com.ferhatayar.dto.DtoRentalIU;

public interface IRentalService {

	public DtoRental saveRental(DtoRentalIU input);
	
	public List<DtoRental> getAllRentalList();
	
	public DtoRental getRentalById(Long id);
	
	public DtoRental deleteRental(Long id);
	
	public DtoRental updateRental(Long id, DtoRentalIU input);
	
}
