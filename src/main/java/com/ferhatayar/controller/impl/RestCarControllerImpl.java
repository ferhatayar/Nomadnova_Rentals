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

import com.ferhatayar.controller.IRestCarController;
import com.ferhatayar.controller.RestBaseController;
import com.ferhatayar.controller.RootEntity;
import com.ferhatayar.dto.DtoCar;
import com.ferhatayar.dto.DtoCarIU;
import com.ferhatayar.service.ICarService;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController{

	@Autowired
	private ICarService carService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@RequestBody DtoCarIU input) {
		return ok(carService.saveCar(input));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoCar>> getAllCarList() {
		return ok(carService.getAllCarList());
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoCar> getCarById(@PathVariable(name = "id") Long id) {
		return ok(carService.getCarById(id));
	}

	@DeleteMapping("/{id}")
	@Override
	public RootEntity<DtoCar> deleteCar(@PathVariable(name = "id") Long id) {
		return ok(carService.deleteCar(id));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCar> updateCar(@PathVariable(name = "id") Long id, @RequestBody DtoCarIU input) {
		return ok(carService.updateCar(id, input));
	}
	
	
	
}
