package com.ferhatayar.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ferhatayar.enums.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	public static final String SECRET_KEY = "/DUT2NEoM0BQb1n+Mk+9hkt9mtG0wofeuoTJ1XLwj0M=";

	public String generateToken(UserDetails userDetails, Role role) {
		 return Jwts.builder()
		 .setSubject(userDetails.getUsername())
		 .addClaims(Map.of("role", role.name()))
		 .setIssuedAt(new Date())
		 .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2))
		 .signWith(getKey(),SignatureAlgorithm.HS256)
		 .compact();
	}
	
	public Claims getClaims(String token) {
		 Claims claims = Jwts.parserBuilder()
		.setSigningKey(getKey())
		.build()
		.parseClaimsJws(token).getBody();
		 return claims;
	}
	
	public <T> T exportToken(String token, Function<Claims, T> claimsFunc) {
		Claims claims = getClaims(token);
		return claimsFunc.apply(claims);
	}
	
	public String getUsernameByToken(String token) {
		return exportToken(token, Claims::getSubject);
	}
	
	public boolean isTokenValid(String token) {
		Date expireDate = exportToken(token, Claims::getExpiration);
		return new Date().before(expireDate);
	}
	
	public String getRoleByToken(String token) {
        return exportToken(token, claims -> claims.get("role", String.class));
    }
 	
	public Key getKey() {
		byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(bytes);
	}
	
}
