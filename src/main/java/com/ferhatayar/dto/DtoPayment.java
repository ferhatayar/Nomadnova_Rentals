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
public class DtoPayment extends DtoBase{
    
    private BigDecimal amount;
    
    private PaymentMethod paymentMethod;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date paymentDate;
    
    private PaymentStatus status;
	
}
