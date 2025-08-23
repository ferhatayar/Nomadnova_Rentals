package com.ferhatayar.model;

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
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Addresses extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "postal_code")
	private String postalCode;
	
}
