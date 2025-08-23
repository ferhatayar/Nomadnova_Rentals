package com.ferhatayar.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ferhatayar.enums.CarStatus;
import com.ferhatayar.enums.FuelType;
import com.ferhatayar.enums.Transmission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCar extends DtoBase{

	private DtoUser owner;
	
    private String brand;
    
    private String model;
    
    private Integer year;
    
    private FuelType fuelType;
    
    private Transmission transmission;
    
    private BigDecimal pricePerDay;
    
    private BigDecimal priceForSale;
    
    private CarStatus status;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createdAt;
    
    private List<DtoCarImages> carImages;
	
}
