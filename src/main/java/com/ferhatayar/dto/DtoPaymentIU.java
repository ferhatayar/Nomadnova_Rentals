package com.ferhatayar.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ferhatayar.enums.PaymentMethod;
import com.ferhatayar.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoPaymentIU {

	 private Long rentalId; 
    
	 private BigDecimal amount;
	 
	 private Long purchaseId;
	 
	 private PaymentMethod paymentMethod;
	 
	 @DateTimeFormat(iso = ISO.DATE_TIME)
	 private Date paymentDate;
	 
	 private PaymentStatus status;
	
}
