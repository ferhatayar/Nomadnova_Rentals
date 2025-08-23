package com.ferhatayar.controller;

import com.ferhatayar.dto.AuthRequest;
import com.ferhatayar.dto.AuthResponse;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
	
}
