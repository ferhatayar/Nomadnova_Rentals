package com.ferhatayar.controller;

import java.util.List;

import com.ferhatayar.dto.DtoPurchase;
import com.ferhatayar.dto.DtoPurchaseIU;

public interface IRestPurchaseController {

	public RootEntity<DtoPurchase> savePurchase(DtoPurchaseIU input);
	
	public RootEntity<List<DtoPurchase>> getAllPurchaseList();
	
	public RootEntity<DtoPurchase> getPurchaseById(Long id);

	public RootEntity<DtoPurchase> deletePurchase(Long id);
	
	public RootEntity<DtoPurchase> updatePurchase(Long id, DtoPurchaseIU input);
}
