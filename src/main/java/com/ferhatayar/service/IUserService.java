package com.ferhatayar.service;

import java.util.List;

import com.ferhatayar.dto.DtoUser;

public interface IUserService {

	public List<DtoUser> getAllUserList();
	
	public DtoUser getUserById(Long id);
	
	public DtoUser deleteUser(Long id);
	
	public DtoUser updateUser(Long id, DtoUser input);
	
}
