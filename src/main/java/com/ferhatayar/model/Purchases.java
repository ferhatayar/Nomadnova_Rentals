package com.ferhatayar.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchases extends BaseEntity{

	@ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
	private Cars car;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private Users user;
	
	@Column(name = "purchase_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date purchaseDate;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
	private Payments payments;
}
