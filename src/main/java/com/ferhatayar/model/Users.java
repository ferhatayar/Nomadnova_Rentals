package com.ferhatayar.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ferhatayar.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity implements UserDetails{

	@Column(name = "user_name", nullable = false)
	private String username;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "created_at")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdAt;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Addresses> addresses;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Rentals> rentals;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Purchases> purchases;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}
	
}
