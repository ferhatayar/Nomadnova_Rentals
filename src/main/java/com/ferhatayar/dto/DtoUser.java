package com.ferhatayar.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ferhatayar.enums.Role;
import com.ferhatayar.model.Purchases;
import com.ferhatayar.model.Rentals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DtoUser extends DtoBase{

	private String username;
	
	private String password;
	
	private String email;
	
	private String phone;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdAt;
	
	private Role role;
	
	private List<DtoAddresses> addresses = new ArrayList<>() ;
	
	private List<DtoRental> rentals = new ArrayList<>() ;
	
	private List<DtoPurchase> purchases = new ArrayList<>() ;
	
}
