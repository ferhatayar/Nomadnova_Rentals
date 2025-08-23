package com.ferhatayar.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoPurchaseIU {

	private Long carId;
	
    private Long userId;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date purchaseDate;
    
    private BigDecimal price;
    
    private Long paymentId;
	
}
