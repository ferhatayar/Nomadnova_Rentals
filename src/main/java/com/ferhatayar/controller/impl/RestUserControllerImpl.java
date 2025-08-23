package com.ferhatayar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferhatayar.controller.IRestUserController;
import com.ferhatayar.controller.RestBaseController;
import com.ferhatayar.controller.RootEntity;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.service.IUserService;

@RestController
@RequestMapping("/rest/api/user")
public class RestUserControllerImpl extends RestBaseController implements IRestUserController{
	
	@Autowired
	private IUserService userService;

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoUser>> getAllUserList() {
		return ok(userService.getAllUserList());
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoUser> getUserById(@PathVariable(name = "id") Long id) {
		return ok(userService.getUserById(id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<DtoUser> deleteUser(@PathVariable(name = "id") Long id) {
		return ok(userService.deleteUser(id));
	}

	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoUser> updateUser(@PathVariable(name = "id") Long id, @RequestBody DtoUser input) {
		return ok(userService.updateUser(id, input));
	}
	
	

}
