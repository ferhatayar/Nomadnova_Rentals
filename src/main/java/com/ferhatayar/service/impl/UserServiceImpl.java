package com.ferhatayar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferhatayar.dto.DtoAddresses;
import com.ferhatayar.dto.DtoPurchase;
import com.ferhatayar.dto.DtoRental;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;
import com.ferhatayar.model.Addresses;
import com.ferhatayar.model.Purchases;
import com.ferhatayar.model.Rentals;
import com.ferhatayar.model.Users;
import com.ferhatayar.repository.AddressesRepository;
import com.ferhatayar.repository.RefreshTokenRepository;
import com.ferhatayar.repository.UserRepository;
import com.ferhatayar.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	private AddressesRepository addressesRepository;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Override
	public List<DtoUser> getAllUserList() {
		
		List<DtoUser> dtoUsers = new ArrayList<>();
		List<Users> users = userRepository.findAll();
		
		for (Users user : users) {
			DtoUser dtoUser = new DtoUser();
			BeanUtils.copyProperties(user, dtoUser);
			
			if(user.getAddresses() != null) {
				DtoAddresses dtoAddress = new DtoAddresses();
				BeanUtils.copyProperties(user.getAddresses(), dtoAddress);
				dtoUser.getAddresses().add(dtoAddress);
			}
			if(user.getRentals() != null) {
				DtoRental dtoRental = new DtoRental();
				BeanUtils.copyProperties(user.getRentals(), dtoRental);
				dtoUser.getRentals().add(dtoRental);
			}
			if(user.getPurchases() != null) {
				DtoPurchase dtoPurchase = new DtoPurchase();
				BeanUtils.copyProperties(user.getPurchases(), dtoPurchase);
				dtoUser.getPurchases().add(dtoPurchase);
			}
			dtoUsers.add(dtoUser);
		}
		return dtoUsers;
	}
	
	private DtoUser userToDto(Long id) {
	    Users user = userRepository.findById(id)
	            .orElseThrow(() -> new BaseExpection(
	                    new ErrorMessage(MessageType.USER_NOT_FOUND, id.toString())
	            ));

	    DtoUser dtoUser = new DtoUser();
	    BeanUtils.copyProperties(user, dtoUser);

	    if (user.getAddresses() != null) {
	        user.getAddresses().forEach(address -> {
	            DtoAddresses dtoAddress = new DtoAddresses();
	            BeanUtils.copyProperties(address, dtoAddress);
	            dtoUser.getAddresses().add(dtoAddress);
	        });
	    }
	    if (user.getRentals() != null) {
	        user.getRentals().forEach(rental -> {
	            DtoRental dtoRental = new DtoRental();
	            BeanUtils.copyProperties(rental, dtoRental);
	            dtoUser.getRentals().add(dtoRental);
	        });
	    }
	    if (user.getPurchases() != null) {
	        user.getPurchases().forEach(purchase -> {
	            DtoPurchase dtoPurchase = new DtoPurchase();
	            BeanUtils.copyProperties(purchase, dtoPurchase);
	            dtoUser.getPurchases().add(dtoPurchase);
	        });
	    }

	    return dtoUser;
	}

	@Override
	public DtoUser getUserById(Long id) {
		return userToDto(id);
	}

	@Override
	@Transactional
	public DtoUser deleteUser(Long id) {
		Users user = userRepository.findById(id)
	            .orElseThrow(() -> new BaseExpection(
	                    new ErrorMessage(MessageType.USER_NOT_FOUND, id.toString())
	            ));
		
		DtoUser dtoUser = userToDto(id);
		refreshTokenRepository.deleteAllByUser(user);
		userRepository.delete(user);
		
		return dtoUser;
	}

	@Override
	public DtoUser updateUser(Long id, DtoUser input) {
		Users user = userRepository.findById(id)
	            .orElseThrow(() -> new BaseExpection(
	                    new ErrorMessage(MessageType.USER_NOT_FOUND, id.toString())
	            ));
		user.setEmail(input.getEmail());
		user.setPhone(input.getPhone());
		user.setRole(input.getRole());
		if (input.getPassword() != null && !input.getPassword().isEmpty()) {
	        user.setPassword(passwordEncoder.encode(input.getPassword()));
	    }
		
		if (input.getAddresses() != null) {
	        user.getAddresses().clear();
	        input.getAddresses().forEach(dtoAddress -> {
	            Addresses address = new Addresses();
	            BeanUtils.copyProperties(dtoAddress, address);
	            address.setUser(user); 
	            user.getAddresses().add(address);
	        });
	    }

	    if (input.getRentals() != null) {
	        user.getRentals().clear();
	        input.getRentals().forEach(dtoRental -> {
	            Rentals rental = new Rentals();
	            BeanUtils.copyProperties(dtoRental, rental);
	            rental.setUser(user);
	            user.getRentals().add(rental);
	        });
	    }

	    if (input.getPurchases() != null) {
	        user.getPurchases().clear();
	        input.getPurchases().forEach(dtoPurchase -> {
	            Purchases purchase = new Purchases();
	            BeanUtils.copyProperties(dtoPurchase, purchase);
	            purchase.setUser(user);
	            user.getPurchases().add(purchase);
	        });
	    }
	    
	    Users updatedUser = userRepository.save(user);
		
		return userToDto(updatedUser.getId());
	}
	
	
	
}
