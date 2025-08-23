package com.ferhatayar.dto;

import com.ferhatayar.model.Users;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddresses extends DtoBase{

	private String title;
	
	private String city;
	
	private String district;
	
	private String street;
	
	private String postalCode;
	
	private DtoUser user;
	
}
