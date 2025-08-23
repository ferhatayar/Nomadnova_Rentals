package com.ferhatayar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferhatayar.dto.DtoCar;
import com.ferhatayar.dto.DtoCarIU;
import com.ferhatayar.dto.DtoCarImages;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;
import com.ferhatayar.model.Cars;
import com.ferhatayar.model.Users;
import com.ferhatayar.repository.CarRepository;
import com.ferhatayar.repository.UserRepository;
import com.ferhatayar.service.ICarService;

@Service
public class CarServiceImpl implements ICarService{

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UserRepository userRepository;

	private Cars createCar(DtoCarIU input) {
		Cars car = new Cars();
		BeanUtils.copyProperties(input, car);
		car.setCreatedAt(new Date());
		Users owner = userRepository.findById(input.getOwnerId())
				.orElseThrow(() -> new BaseExpection(
						new ErrorMessage(MessageType.OWNER_NOT_FOUND,input.getOwnerId().toString())));
		car.setOwner(owner);
		return car;
	}
	
	@Override
	public DtoCar saveCar(DtoCarIU input) {
		DtoCar dtoCar = new DtoCar();
		DtoUser dtoOwner = new DtoUser();
		
		Cars savedCar = carRepository.save(createCar(input));
		BeanUtils.copyProperties(savedCar, dtoCar);
		BeanUtils.copyProperties(savedCar.getOwner(), dtoOwner);
		dtoCar.setOwner(dtoOwner);
		
		return dtoCar;
	}

	@Override
	public List<DtoCar> getAllCarList() {
		List<DtoCar> dtoCarList = new ArrayList<>();
		List<Cars> cars = carRepository.findAll();
		
		for (Cars car : cars) {
			DtoCar dtoCar = new DtoCar();
			BeanUtils.copyProperties(car, dtoCar);
			
			if(car.getOwner() != null) {
				DtoUser dtoOwner = new DtoUser();
				BeanUtils.copyProperties(car.getOwner(), dtoOwner);
				dtoCar.setOwner(dtoOwner);
			}
			if(car.getCarImages() != null) {
				car.getCarImages().forEach(image -> {
					DtoCarImages dtoImage = new DtoCarImages();
					BeanUtils.copyProperties(image, dtoImage);
					dtoCar.getCarImages().add(dtoImage);
				});
			}
			dtoCarList.add(dtoCar);
		}
		
		return dtoCarList;
	}
	
	private DtoCar carToDto(Long id) {
		DtoCar dtoCar = new DtoCar();
		Cars car = carRepository.findById(id)
				.orElseThrow(() -> new BaseExpection(
						new ErrorMessage(MessageType.CAR_NOT_FOUND,id.toString())));
		BeanUtils.copyProperties(car, dtoCar);
		
		DtoUser dtoOwner = new DtoUser();
		BeanUtils.copyProperties(car.getOwner(), dtoOwner);
		dtoCar.setOwner(dtoOwner);
		
		if(car.getCarImages() != null) {
			car.getCarImages().forEach(image -> {
				DtoCarImages dtoCarImages = new DtoCarImages();
				BeanUtils.copyProperties(image, dtoCarImages);
				dtoCar.getCarImages().add(dtoCarImages);
			});
		}
		return dtoCar;
		
	}

	@Override
	public DtoCar getCarById(Long id) {
		return carToDto(id);
	}

	@Override
	public DtoCar deleteCar(Long id) {
		Cars car = carRepository.findById(id)
	            .orElseThrow(() -> new BaseExpection(
	                    new ErrorMessage(MessageType.CAR_NOT_FOUND, id.toString())));
		DtoCar dtoCar = carToDto(id);
		carRepository.delete(car);
		    
		return dtoCar;
	}

	@Override
	public DtoCar updateCar(Long id, DtoCarIU input) {
		Cars car = carRepository.findById(id)
				.orElseThrow(() -> new BaseExpection(
						new ErrorMessage(MessageType.CAR_NOT_FOUND,id.toString())));
		car.setBrand(input.getBrand());
		car.setFuelType(input.getFuelType());
		car.setModel(input.getModel());
		car.setYear(input.getYear());
		car.setTransmission(input.getTransmission());
		car.setPricePerDay(input.getPricePerDay());
		car.setPriceForSale(input.getPriceForSale());
		car.setStatus(input.getStatus());
		
		if(car.getOwner() != null) {
			Users owner = userRepository.findById(car.getOwner().getId())
					.orElseThrow(() -> new BaseExpection(
							new ErrorMessage(MessageType.OWNER_NOT_FOUND,car.getOwner().getUsername())));
			car.setOwner(owner);
		}
		
		Cars updateCar = carRepository.save(car);
		
		return carToDto(updateCar.getId());
	}
	
	
}
