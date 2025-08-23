package com.ferhatayar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferhatayar.dto.DtoAddresses;
import com.ferhatayar.dto.DtoAddressesIU;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;
import com.ferhatayar.model.Addresses;
import com.ferhatayar.model.Users;
import com.ferhatayar.repository.AddressesRepository;
import com.ferhatayar.repository.UserRepository;
import com.ferhatayar.service.IAddressesService;

@Service
public class AddressesServiceImpl implements IAddressesService{

	@Autowired
	private AddressesRepository addressesRepository;
	
	@Autowired
	private UserRepository userRepository;

	private Addresses createAddresses(DtoAddressesIU dtoAddressesIU) {
		Addresses addresses = new Addresses();
		BeanUtils.copyProperties(dtoAddressesIU, addresses);
		Optional<Users> optUser = userRepository.findById(dtoAddressesIU.getUserId());
		if(optUser.isEmpty()) {
			throw new BaseExpection(new ErrorMessage(MessageType.USER_NOT_FOUND,optUser.get().getUsername()));
		}
		addresses.setUser(optUser.get());
		
		return addresses;
	}
	
	@Override
	public DtoAddresses saveAddresses(DtoAddressesIU dtoAddressesIU) {
		DtoAddresses dtoAddresses = new DtoAddresses();
		DtoUser user = new DtoUser();
		
		Addresses savedAddresses = addressesRepository.save(createAddresses(dtoAddressesIU));
		BeanUtils.copyProperties(savedAddresses, dtoAddresses);
		BeanUtils.copyProperties(savedAddresses.getUser(), user);
		dtoAddresses.setUser(user);
		
		return dtoAddresses;
	}

	@Override
	public List<DtoAddresses> getAllAddressesList() {
		List<DtoAddresses> dtoAddresses = new ArrayList<>();
		List<Addresses> addresses = addressesRepository.findAll();
		
		for (Addresses address : addresses) {
			DtoAddresses dtoAddress = new DtoAddresses();
			BeanUtils.copyProperties(address, dtoAddress);
			
			 if (address.getUser() != null) {
		            DtoUser dtoUser = new DtoUser();
		            BeanUtils.copyProperties(address.getUser(), dtoUser);
		            dtoAddress.setUser(dtoUser);;
		        }
			
			dtoAddresses.add(dtoAddress);
		}
		return dtoAddresses;
	}
	
	public DtoAddresses addresstoDto(Long id) {
		DtoAddresses dtoAddress = new DtoAddresses();
		DtoUser dtoUser = new DtoUser();
		
		Optional<Addresses> optAddress = addressesRepository.findById(id);
		if(optAddress.isEmpty()) {
			throw new BaseExpection(new ErrorMessage(MessageType.ADDRESS_NOT_FOUND,id.toString()));
		}
		
		Addresses address = optAddress.get();
		BeanUtils.copyProperties(address, dtoAddress);
		BeanUtils.copyProperties(address.getUser(), dtoUser);
		dtoAddress.setUser(dtoUser);
		
		return dtoAddress;
	}

	@Override
	public DtoAddresses getAddressById(Long id) {
		return addresstoDto(id);
	}

	@Override
	public DtoAddresses deleteAddress(Long id) {
		
		DtoAddresses dtoAddress = addresstoDto(id);
		
		addressesRepository.deleteById(id);
		
		return dtoAddress;
	}

	@Override
	public DtoAddresses updateAddress(Long id, DtoAddressesIU dtoAddressesIU) {
	    Addresses address = addressesRepository.findById(id)
	            .orElseThrow(() -> new BaseExpection(
	                    new ErrorMessage(MessageType.ADDRESS_NOT_FOUND, "id")
	            ));

	    address.setCity(dtoAddressesIU.getCity());
	    address.setDistrict(dtoAddressesIU.getDistrict());
	    address.setPostalCode(dtoAddressesIU.getPostalCode());
	    address.setStreet(dtoAddressesIU.getStreet());
	    address.setTitle(dtoAddressesIU.getTitle());

	    Users user = userRepository.findById(dtoAddressesIU.getUserId())
	            .orElseThrow(() -> new BaseExpection(
	                    new ErrorMessage(MessageType.USER_NOT_FOUND, "dtoAddressesIU.getUserId()")
	            ));
	    address.setUser(user);

	    Addresses updated = addressesRepository.save(address);

	    DtoAddresses dtoAddress = new DtoAddresses();
	    BeanUtils.copyProperties(updated, dtoAddress);

	    DtoUser dtoUser = new DtoUser();
	    BeanUtils.copyProperties(user, dtoUser);
	    dtoAddress.setUser(dtoUser);

	    return dtoAddress;
	}
	
}
