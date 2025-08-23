package com.ferhatayar.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ferhatayar.enums.RentalsStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rentals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rentals extends BaseEntity{

	@ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
	private Cars car;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private Users user;
	
	@Column(name = "start_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date startDate;
	
	@Column(name = "end_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date endDate;
	
	@Column(name = "total_price")
	private BigDecimal totalPrice;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private RentalsStatus status;
	
	@OneToOne(mappedBy = "rental")
	private Payments payments;
}
