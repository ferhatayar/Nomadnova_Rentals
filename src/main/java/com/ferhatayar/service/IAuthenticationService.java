package com.ferhatayar.service;

import com.ferhatayar.dto.AuthRequest;
import com.ferhatayar.dto.AuthResponse;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
	
}
