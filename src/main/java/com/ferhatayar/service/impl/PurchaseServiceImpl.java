package com.ferhatayar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferhatayar.dto.DtoCar;
import com.ferhatayar.dto.DtoPayment;
import com.ferhatayar.dto.DtoPurchase;
import com.ferhatayar.dto.DtoPurchaseIU;
import com.ferhatayar.dto.DtoUser;
import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;
import com.ferhatayar.model.Cars;
import com.ferhatayar.model.Payments;
import com.ferhatayar.model.Purchases;
import com.ferhatayar.model.Users;
import com.ferhatayar.repository.CarRepository;
import com.ferhatayar.repository.PaymentRepository;
import com.ferhatayar.repository.PurchaseRepository;
import com.ferhatayar.repository.UserRepository;
import com.ferhatayar.service.IPurchaseService;

@Service
public class PurchaseServiceImpl implements IPurchaseService{

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	private Purchases createDtoPurchase(DtoPurchaseIU input) {
		Purchases purchase = new Purchases();
		BeanUtils.copyProperties(input, purchase);
		
		Cars car = carRepository.findById(input.getCarId())
				.orElseThrow(() -> new BaseExpection(
						new ErrorMessage(MessageType.CAR_NOT_FOUND,input.getCarId().toString())));
		
		Users user = userRepository.findById(input.getUserId())
				.orElseThrow(() -> new BaseExpection(
						new ErrorMessage(MessageType.USER_NOT_FOUND,input.getUserId().toString())));
		
		Payments payment = paymentRepository.findById(input.getPaymentId())
				.orElseThrow(() -> new BaseExpection(
						new ErrorMessage(MessageType.PAYMENT_NOT_FOUND,input.getPaymentId().toString())));
		
		purchase.setCar(car);
		purchase.setUser(user);
		purchase.setPayments(payment);
		return purchase;
	}
	
	@Override
	public DtoPurchase savePurchase(DtoPurchaseIU input) {
		DtoPurchase dtoPurchase = new DtoPurchase();
		DtoCar dtoCar = new DtoCar();
		DtoUser dtoUser = new DtoUser();
		DtoPayment dtoPayment = new DtoPayment();
		
		Purchases savedPurchase = purchaseRepository.save(createDtoPurchase(input));
		BeanUtils.copyProperties(savedPurchase, dtoPurchase);
		BeanUtils.copyProperties(savedPurchase.getCar(), dtoCar);
		BeanUtils.copyProperties(savedPurchase.getPayments(), dtoPayment);
		BeanUtils.copyProperties(savedPurchase.getUser(), dtoUser);
		
		dtoPurchase.setCar(dtoCar);
		dtoPurchase.setPayment(dtoPayment);
		dtoPurchase.setUser(dtoUser);
		
		return dtoPurchase;
	}

	@Override
	public List<DtoPurchase> getAllPurchaseList() {
		List<DtoPurchase> purchaseList = new ArrayList<>();
		List<Purchases> purchases = purchaseRepository.findAll();
		
		for (Purchases purchase : purchases) {
			DtoPurchase dtoPurchase = new DtoPurchase();
			BeanUtils.copyProperties(purchase, dtoPurchase);
			
			if(purchase.getCar() != null) {
				DtoCar dtoCar = new DtoCar();
				BeanUtils.copyProperties(purchase.getCar(), dtoCar);
				dtoPurchase.setCar(dtoCar);
			}
			if(purchase.getUser() != null) {
				DtoUser dtoUser = new DtoUser();
				BeanUtils.copyProperties(purchase.getUser(), dtoUser);
				dtoPurchase.setUser(dtoUser);
			}
			if(purchase.getPayments() != null) {
				DtoPayment dtoPayment = new DtoPayment();
				BeanUtils.copyProperties(purchase.getPayments(), dtoPayment);
				dtoPurchase.setPayment(dtoPayment);
			}
			purchaseList.add(dtoPurchase);
		}
		
		return purchaseList;
	}
	
	private DtoPurchase purchaseToDto(Long id) {
		DtoPurchase dtoPurchase = new DtoPurchase();
		DtoCar dtoCar = new DtoCar();
		DtoUser dtoUser = new DtoUser();
		DtoPayment dtoPayment = new DtoPayment();
		
		Purchases purchase = purchaseRepository.findById(id)
				.orElseThrow(() -> new BaseExpection(
						new ErrorMessage(MessageType.PURCHASE_NOT_FOUND,id.toString())));
		
		BeanUtils.copyProperties(purchase, dtoPurchase);
		BeanUtils.copyProperties(purchase.getCar(), dtoCar);
		BeanUtils.copyProperties(purchase.getUser(), dtoUser);
		BeanUtils.copyProperties(purchase.getPayments(), dtoPayment);
		
		dtoPurchase.setCar(dtoCar);
		dtoPurchase.setUser(dtoUser);
		dtoPurchase.setPayment(dtoPayment);
		
		return dtoPurchase;
	}

	@Override
	public DtoPurchase getPurchaseById(Long id) {
		return purchaseToDto(id);
	}

	@Override
	@Transactional
	public DtoPurchase deletePurchase(Long id) {
	    Purchases purchase = purchaseRepository.findById(id)
	            .orElseThrow(() -> new BaseExpection(
	                    new ErrorMessage(MessageType.PURCHASE_NOT_FOUND, id.toString())));

	    DtoPurchase dtoPurchase = purchaseToDto(id);
	    if (purchase.getPayments() != null) {
	        paymentRepository.delete(purchase.getPayments());
	    }
	    purchaseRepository.delete(purchase);
	    return dtoPurchase;
	}

	@Override
	public DtoPurchase updatePurchase(Long id, DtoPurchaseIU input) {
		Purchases purchase = purchaseRepository.findById(id)
				.orElseThrow(() -> new BaseExpection(
						new ErrorMessage(MessageType.PURCHASE_NOT_FOUND,id.toString())));
		
		purchase.setPrice(input.getPrice());
		purchase.setPurchaseDate(input.getPurchaseDate());
		
		if (input.getCarId() != null) {
			Cars car = carRepository.findById(input.getCarId())
					.orElseThrow(() -> new BaseExpection(
							new ErrorMessage(MessageType.CAR_NOT_FOUND,input.getCarId().toString())));
			purchase.setCar(car);
		}
		if (input.getUserId() != null) {
			Users user = userRepository.findById(input.getUserId())
					.orElseThrow(() -> new BaseExpection(
							new ErrorMessage(MessageType.USER_NOT_FOUND,input.getUserId().toString())));
			purchase.setUser(user);
		}
		if (input.getPaymentId() != null) {
			Payments payment = paymentRepository.findById(input.getPaymentId())
					.orElseThrow(() -> new BaseExpection(
							new ErrorMessage(MessageType.PAYMENT_NOT_FOUND,input.getPaymentId().toString())));
			purchase.setPayments(payment);
		}
		
		Purchases updatePurchase = purchaseRepository.save(purchase);
		
		DtoPurchase dtoPurchase = new DtoPurchase();
		BeanUtils.copyProperties(updatePurchase, dtoPurchase);
		
		DtoCar dtoCar = new DtoCar();
		DtoPayment dtoPayment = new DtoPayment();
		DtoUser dtoUser = new DtoUser();
		
		BeanUtils.copyProperties(updatePurchase.getCar(), dtoCar);
		BeanUtils.copyProperties(updatePurchase.getPayments(), dtoPayment);
		BeanUtils.copyProperties(updatePurchase.getUser(), dtoUser);
		dtoPurchase.setCar(dtoCar);
		dtoPurchase.setPayment(dtoPayment);
		dtoPurchase.setUser(dtoUser);
		
		return dtoPurchase;
	}
	
	
	
}
