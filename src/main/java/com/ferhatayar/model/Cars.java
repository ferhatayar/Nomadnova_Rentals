package com.ferhatayar.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ferhatayar.enums.CarStatus;
import com.ferhatayar.enums.FuelType;
import com.ferhatayar.enums.Transmission;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cars extends BaseEntity{

	@ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
	private Users owner;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "fuel_type")
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	
	@Column(name = "transmission")
	@Enumerated(EnumType.STRING)
	private Transmission transmission;
	
	@Column(name = "price_per_day")
	private BigDecimal pricePerDay;
	
	@Column(name = "price_for_sale")
	private BigDecimal priceForSale;	
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private CarStatus status;
	
	@Column(name = "created_at")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdAt;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<CarImages> carImages;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<Rentals> rentals;
	
	@OneToOne(mappedBy = "car")
	private Purchases purchases;
	
}
