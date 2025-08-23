package com.ferhatayar.model;

import java.io.ObjectInputFilter.Status;
import java.math.BigDecimal;

import com.ferhatayar.enums.FuelType;
import com.ferhatayar.enums.Transmission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarImages extends BaseEntity{

	@ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
	private Cars car;
	
	@Column(name = "image_url")
	private String imageUrl;
	
}
