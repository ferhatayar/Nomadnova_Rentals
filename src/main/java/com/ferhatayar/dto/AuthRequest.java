package com.ferhatayar.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ferhatayar.enums.Role;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	private String email;
	
	private String phone;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdAt;
	
	private Role role;
	
}
