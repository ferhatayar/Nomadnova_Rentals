package com.ferhatayar.service;

import java.util.List;

import com.ferhatayar.dto.DtoPayment;
import com.ferhatayar.dto.DtoPaymentIU;

public interface IPaymentService {

	public DtoPayment savePayment(DtoPaymentIU input);
	
	public List<DtoPayment> getAllPaymentList();
	
	public DtoPayment getPaymentById(Long id);
	
	public DtoPayment deletePayment(Long id);
	
	public DtoPayment updatePayment(Long id, DtoPaymentIU input);
}
