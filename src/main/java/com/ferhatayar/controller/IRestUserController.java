package com.ferhatayar.controller;

import java.util.List;

import com.ferhatayar.dto.DtoUser;

public interface IRestUserController {

	public RootEntity<List<DtoUser>> getAllUserList();
	
	public RootEntity<DtoUser> getUserById(Long id);
	
	public RootEntity<DtoUser> deleteUser(Long id);
	
	public RootEntity<DtoUser> updateUser(Long id, DtoUser input);
	
}
