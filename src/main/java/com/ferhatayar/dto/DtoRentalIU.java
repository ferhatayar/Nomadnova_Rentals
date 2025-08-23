package com.ferhatayar.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ferhatayar.enums.RentalsStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoRentalIU {

	private Long carId;
	
    private Long userId;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date startDate;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date endDate;
    
    private BigDecimal totalPrice;
    
    private RentalsStatus status;
	
}
