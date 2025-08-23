package com.ferhatayar.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddressesIU {

	@NotEmpty
	private String title;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String district;
	
	@NotEmpty
	private String street;

	@NotEmpty
	private String postalCode;
	
	@NotNull
	private Long userId;
	
}
