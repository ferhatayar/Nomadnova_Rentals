package com.ferhatayar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferhatayar.controller.IRestAddressesController;
import com.ferhatayar.controller.RestBaseController;
import com.ferhatayar.controller.RootEntity;
import com.ferhatayar.dto.DtoAddresses;
import com.ferhatayar.dto.DtoAddressesIU;
import com.ferhatayar.service.IAddressesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressesControllerImpl extends RestBaseController implements IRestAddressesController{

	@Autowired
	private IAddressesService addressesService;


	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddresses> saveAddresses(@Valid @RequestBody DtoAddressesIU dtoAddressesIU) {
		return ok(addressesService.saveAddresses(dtoAddressesIU));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoAddresses>> getAllAddressesList() {
		return ok(addressesService.getAllAddressesList());
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoAddresses> getAddressById(@PathVariable(name = "id",required = true) Long id) {
		return ok(addressesService.getAddressById(id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<DtoAddresses> deleteAddress(@PathVariable(name = "id" , required = true) Long id) {
		return ok(addressesService.deleteAddress(id));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoAddresses> updateAddress(@PathVariable(name="id",required = true) Long id, @RequestBody DtoAddressesIU dtoAddressesIU) {
		return ok(addressesService.updateAddress(id, dtoAddressesIU));
	}

	
}
