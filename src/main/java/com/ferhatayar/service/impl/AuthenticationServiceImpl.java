package com.ferhatayar.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ferhatayar.dto.AuthRequest;
import com.ferhatayar.dto.AuthResponse;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.dto.RefreshTokenRequest;
import com.ferhatayar.enums.Role;
import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;
import com.ferhatayar.jwt.JWTService;
import com.ferhatayar.model.RefreshToken;
import com.ferhatayar.model.Users;
import com.ferhatayar.repository.RefreshTokenRepository;
import com.ferhatayar.repository.UserRepository;
import com.ferhatayar.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	private Users createUser(AuthRequest input) {
		Users user = new Users();
		user.setCreatedAt(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setEmail(input.getEmail());
		user.setPhone(input.getPhone());
		user.setRole(input.getRole());
		return user;
	}
	
	private RefreshToken createRefreshToken(Users user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
	}
	
	@Override
	public DtoUser register(AuthRequest input) {
		DtoUser dtoUser = new DtoUser();
		Users savedUser = userRepository.save(createUser(input));
		
		BeanUtils.copyProperties(savedUser,dtoUser);
		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
			authenticationProvider.authenticate(authentication);
			
			Optional<Users> optUser = userRepository.findByUsername(input.getUsername());
			Role role = optUser.get().getRole();
			
			String accessToken = jwtService.generateToken(optUser.get(),role);
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));
			
			return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
			
		} catch (Exception e) {
			throw new BaseExpection(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID,e.getMessage()));
		}
	}
	
	public boolean isValidRefreshToken(Date expireDate) {
		return new Date().before(expireDate);
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest input) {
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
		if(optRefreshToken.isEmpty()) {
			throw new BaseExpection(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND,input.getRefreshToken()));
		}
		
		if(!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
			throw new BaseExpection(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED,input.getRefreshToken()));
		}
		Users user = optRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user, user.getRole());
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
		
		return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
	}

}
