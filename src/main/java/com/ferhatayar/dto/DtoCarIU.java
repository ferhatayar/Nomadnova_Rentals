package com.ferhatayar.dto;

import java.math.BigDecimal;

import com.ferhatayar.enums.CarStatus;
import com.ferhatayar.enums.FuelType;
import com.ferhatayar.enums.Transmission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCarIU {

	 private Long ownerId;   
	 
	 private String brand;
	 
	 private String model;
	 
	 private Integer year;
	 
	 private FuelType fuelType;
	 
	 private Transmission transmission;
	 
	 private BigDecimal pricePerDay;
	 
	 private BigDecimal priceForSale;
	 
	 private CarStatus status;
	
}
