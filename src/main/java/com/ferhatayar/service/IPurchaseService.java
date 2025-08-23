package com.ferhatayar.service;

import java.util.List;

import com.ferhatayar.dto.DtoPurchase;
import com.ferhatayar.dto.DtoPurchaseIU;

public interface IPurchaseService {

	public DtoPurchase savePurchase(DtoPurchaseIU input);
	
	public List<DtoPurchase> getAllPurchaseList();
	
	public DtoPurchase getPurchaseById(Long id);
	
	public DtoPurchase deletePurchase(Long id);
	
	public DtoPurchase updatePurchase(Long id, DtoPurchaseIU input);
	
}
