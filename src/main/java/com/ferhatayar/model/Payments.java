package com.ferhatayar.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ferhatayar.enums.PaymentMethod;
import com.ferhatayar.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payments extends BaseEntity{

	@OneToOne
    @JoinColumn(name = "rental_id")
	private Rentals rental;
	
	@OneToOne
    @JoinColumn(name = "purchase_id")
	private Purchases purchase;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "payment_method")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Column(name = "payment_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date paymentDate; 
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
}
