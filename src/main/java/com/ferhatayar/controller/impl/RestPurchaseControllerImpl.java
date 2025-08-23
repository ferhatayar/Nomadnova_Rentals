package com.ferhatayar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferhatayar.controller.IRestPurchaseController;
import com.ferhatayar.controller.RestBaseController;
import com.ferhatayar.controller.RootEntity;
import com.ferhatayar.dto.DtoPurchase;
import com.ferhatayar.dto.DtoPurchaseIU;
import com.ferhatayar.service.IPurchaseService;

@RestController
@RequestMapping("/rest/api/purchase")
public class RestPurchaseControllerImpl extends RestBaseController implements IRestPurchaseController{

	@Autowired
	private IPurchaseService purchaseService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoPurchase> savePurchase(@RequestBody DtoPurchaseIU input) {		
		return ok(purchaseService.savePurchase(input));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoPurchase>> getAllPurchaseList() {
		return ok(purchaseService.getAllPurchaseList());
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoPurchase> getPurchaseById(@PathVariable(name = "id") Long id) {
		return ok(purchaseService.getPurchaseById(id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<DtoPurchase> deletePurchase(@PathVariable(name = "id") Long id) {
		return ok(purchaseService.deletePurchase(id));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoPurchase> updatePurchase(@PathVariable(name = "id") Long id, @RequestBody DtoPurchaseIU input) {
		return ok(purchaseService.updatePurchase(id, input));
	}
	
	
	
}
