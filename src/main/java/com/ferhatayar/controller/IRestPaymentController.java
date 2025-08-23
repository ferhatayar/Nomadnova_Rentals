package com.ferhatayar.controller;

import java.util.List;

import com.ferhatayar.dto.DtoPayment;
import com.ferhatayar.dto.DtoPaymentIU;

public interface IRestPaymentController {

	public RootEntity<DtoPayment> savePayment(DtoPaymentIU input);
	
	public RootEntity<List<DtoPayment>> getAllPaymentList();
	
	public RootEntity<DtoPayment> getPaymentById(Long id);
	
	public RootEntity<DtoPayment> deletePayment(Long id);
	
	public RootEntity<DtoPayment> updatePayment(Long id, DtoPaymentIU input);
}
	
}
