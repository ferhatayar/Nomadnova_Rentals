package com.ferhatayar.config;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ferhatayar.handler.AuthEntryPoint;
import com.ferhatayar.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public static final String REGISTER = "/register";
	public static final String AUTHENTICATE = "/authenticate";
	public static final String REFRESH_TOKEN = "/refreshToken";
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	      .cors(Customizer.withDefaults())
	      .csrf(csrf -> csrf.disable())
	      .authorizeHttpRequests(auth -> auth
	          .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	          .requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN).permitAll()
	          .requestMatchers(HttpMethod.GET, "/**").permitAll()
	          .anyRequest().authenticated()
	      )
	      .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
	      .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	      .authenticationProvider(authenticationProvider)
	      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration cfg = new CorsConfiguration();
	    cfg.setAllowedOrigins(List.of("http://localhost:5173"));
	    cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
	    cfg.setAllowedHeaders(List.of("Authorization","Content-Type","Accept","Origin"));
	    cfg.setExposedHeaders(List.of("Authorization"));
	    cfg.setAllowCredentials(true);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", cfg);
	    return source;
	}
	
}
