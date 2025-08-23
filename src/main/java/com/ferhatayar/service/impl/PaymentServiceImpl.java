package com.ferhatayar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferhatayar.dto.DtoPayment;
import com.ferhatayar.dto.DtoPaymentIU;
import com.ferhatayar.dto.DtoPurchase;
import com.ferhatayar.dto.DtoRental;
import com.ferhatayar.expection.BaseExpection;
import com.ferhatayar.expection.ErrorMessage;
import com.ferhatayar.expection.MessageType;
import com.ferhatayar.model.Payments;
import com.ferhatayar.model.Purchases;
import com.ferhatayar.model.Rentals;
import com.ferhatayar.repository.PaymentRepository;
import com.ferhatayar.repository.PurchaseRepository;
import com.ferhatayar.repository.RentalRepository;
import com.ferhatayar.service.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;

	private Payments createPayment(DtoPaymentIU input) {
		Payments payment = new Payments();
		BeanUtils.copyProperties(input, payment);
		
		Rentals rental = rentalRepository.findById(input.getRentalId())
				.orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.RENTAL_NOT_FOUND,input.getRentalId().toString())));
		payment.setRental(rental);
		
		Purchases purchase = purchaseRepository.findById(input.getPurchaseId())
				.orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.PURCHASE_NOT_FOUND,input.getPurchaseId().toString())));
		payment.setPurchase(purchase);
		
		return payment;		
	}
	
	@Override
	public DtoPayment savePayment(DtoPaymentIU input) {
		DtoPayment dtoPayment = new DtoPayment();
		DtoRental dtoRental = new DtoRental();
		DtoPurchase dtoPurchase = new DtoPurchase();

		Payments payment = paymentRepository.save(createPayment(input));
		BeanUtils.copyProperties(payment, dtoPayment);
		BeanUtils.copyProperties(payment.getRental(), dtoRental);
		BeanUtils.copyProperties(payment.getPurchase(), dtoPurchase);
		dtoPayment.setRental(dtoRental);
		dtoPayment.setPurchase(dtoPurchase);
		
		return dtoPayment;
	}

	@Override
	public List<DtoPayment> getAllPaymentList() {
		List<DtoPayment> dtoPaymentList = new ArrayList<>();
		List<Payments> payments = paymentRepository.findAll();
		
		for (Payments payment : payments) {
			DtoPayment dtoPayment = new DtoPayment();
			BeanUtils.copyProperties(payment, dtoPayment);
			
			if(payment.getRental() != null) {
				DtoRental dtoRental = new DtoRental();
				BeanUtils.copyProperties(payment.getRental(), dtoRental);
				dtoPayment.setRental(dtoRental);
			}
			if(payment.getPurchase() != null) {
				DtoPurchase dtoPurchase = new DtoPurchase();
				BeanUtils.copyProperties(payment.getPurchase(), dtoPurchase);
				dtoPayment.setPurchase(dtoPurchase);
			}
			dtoPaymentList.add(dtoPayment);
		}
		return dtoPaymentList;
	}

	private DtoPayment paymentToDto(Long id) {
		DtoPayment dtoPayment = new DtoPayment();
		DtoRental dtoRental = new DtoRental();
		DtoPurchase dtoPurchase = new DtoPurchase();
		
		Payments payment = paymentRepository.findById(id)
				.orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.PAYMENT_NOT_FOUND,id.toString())));
		BeanUtils.copyProperties(payment, dtoPayment);
		BeanUtils.copyProperties(payment.getRental(), dtoRental);
		BeanUtils.copyProperties(payment.getPurchase(), dtoPurchase);
		dtoPayment.setRental(dtoRental);
		dtoPayment.setPurchase(dtoPurchase);
		
		return dtoPayment;
	}
	
	@Override
	public DtoPayment getPaymentById(Long id) {
		return paymentToDto(id);
	}

	@Override
	@Transactional
	public DtoPayment deletePayment(Long id) {
		Payments payment = paymentRepository.findById(id)
				.orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.PAYMENT_NOT_FOUND,id.toString())));
		
		DtoPayment dtoPayment = paymentToDto(id);
		paymentRepository.delete(payment);
		
		return dtoPayment;
	}

	@Override
	public DtoPayment updatePayment(Long id, DtoPaymentIU input) {
		Payments payment = paymentRepository.findById(id)
				.orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.PAYMENT_NOT_FOUND,id.toString())));
		payment.setAmount(input.getAmount());
		payment.setPaymentDate(input.getPaymentDate());
		payment.setPaymentMethod(input.getPaymentMethod());
		payment.setStatus(input.getStatus());
		
		Rentals rentals = rentalRepository.findById(input.getRentalId())
				.orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.RENTAL_NOT_FOUND,input.getRentalId().toString())));
		payment.setRental(rentals);
		
		Purchases purchase = purchaseRepository.findById(input.getPurchaseId())
				.orElseThrow(() -> new BaseExpection(new ErrorMessage(MessageType.PURCHASE_NOT_FOUND,input.getPurchaseId().toString())));
		payment.setPurchase(purchase);
		
		Payments updatedPayment = paymentRepository.save(payment);
		DtoPayment dtoPayment = new DtoPayment();
		DtoRental dtoRental = new DtoRental();
		DtoPurchase dtoPurchase = new DtoPurchase();
		
		BeanUtils.copyProperties(updatedPayment, dtoPayment);
		BeanUtils.copyProperties(updatedPayment.getRental(), dtoRental);
		BeanUtils.copyProperties(updatedPayment.getPurchase(), dtoPurchase);
		dtoPayment.setRental(dtoRental);
		dtoPayment.setPurchase(dtoPurchase);
		
		return dtoPayment;
	}
	
}
