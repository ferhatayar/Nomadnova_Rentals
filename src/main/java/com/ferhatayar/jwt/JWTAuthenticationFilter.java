package com.ferhatayar.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		if(header == null) {
			filterChain.doFilter(request, response);
			return;
		}
		String token;
		String username;
		
		token = header.substring(7);
		try {
			username = jwtService.getUsernameByToken(token);
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if(userDetails != null && jwtService.isTokenValid(token)) {
					UsernamePasswordAuthenticationToken authenticationToken = 
							new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
					
					authenticationToken.setDetails(userDetails);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		} catch (ExpiredJwtException ex) {
			throw new BaseExpection(new ErrorMessage(MessageType.TOKEN_IS_EXPİRED,ex.getMessage()));
		} catch (Exception e) {
			throw new BaseExpection(new ErrorMessage(MessageType.GENERAL_EXPECTION,e.getMessage()));
		}
		filterChain.doFilter(request, response);
	}
	
}
