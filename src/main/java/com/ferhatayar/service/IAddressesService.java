package com.ferhatayar.service;

import java.util.List;

import com.ferhatayar.dto.DtoAddresses;
import com.ferhatayar.dto.DtoAddressesIU;

public interface IAddressesService {

	public DtoAddresses saveAddresses(DtoAddressesIU dtoAddressesIU);
	
	public List<DtoAddresses> getAllAddressesList();
	
	public DtoAddresses getAddressById(Long id);
	
	public DtoAddresses deleteAddress(Long id);
	
	public DtoAddresses updateAddress(Long id,DtoAddressesIU dtoAddressesIU);
	
}
