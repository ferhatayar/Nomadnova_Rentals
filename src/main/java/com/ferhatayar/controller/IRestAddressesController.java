package com.ferhatayar.controller;

import java.util.List;

import com.ferhatayar.dto.DtoAddresses;
import com.ferhatayar.dto.DtoAddressesIU;

public interface IRestAddressesController {

	public RootEntity<DtoAddresses> saveAddresses(DtoAddressesIU dtoAddressesIU);
	
	public RootEntity<List<DtoAddresses>> getAllAddressesList();
	
	public RootEntity<DtoAddresses> getAddressById(Long id);
	
	public RootEntity<DtoAddresses> deleteAddress(Long id);
	
	public RootEntity<DtoAddresses> updateAddress(Long id,DtoAddressesIU dtoAddressesIU);
	
}
