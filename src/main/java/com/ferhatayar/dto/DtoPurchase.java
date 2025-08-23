package com.ferhatayar.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoPurchase extends DtoBase{
	
    private DtoCar car;   
    
    private DtoUser user;  
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date purchaseDate;
    
    private BigDecimal price;
    
    private DtoPayment payment; 

}
