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

import com.ferhatayar.controller.IRestRentalController;
import com.ferhatayar.controller.RestBaseController;
import com.ferhatayar.controller.RootEntity;
import com.ferhatayar.dto.DtoRental;
import com.ferhatayar.dto.DtoRentalIU;
import com.ferhatayar.service.IRentalService;

@RestController
@RequestMapping("rest/api/rental")
public class RestRentalControllerImpl extends RestBaseController implements IRestRentalController{

	@Autowired
	private IRentalService rentalService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoRental> saveRental(@RequestBody DtoRentalIU input) {
		return ok(rentalService.saveRental(input));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoRental>> getAllRentalList() {
		return ok(rentalService.getAllRentalList());
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoRental> getRentalById(@PathVariable(name = "id") Long id) {
		return ok(rentalService.getRentalById(id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<DtoRental> deleteRental(@PathVariable(name = "id") Long id) {
		return ok(rentalService.deleteRental(id));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoRental> updateRental(@PathVariable(name = "id") Long id, @RequestBody DtoRentalIU input) {
		return ok(rentalService.updateRental(id, input));
	}
	
	
	
}
