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

import com.ferhatayar.controller.IRestPaymentController;
import com.ferhatayar.controller.RestBaseController;
import com.ferhatayar.controller.RootEntity;
import com.ferhatayar.dto.DtoPayment;
import com.ferhatayar.dto.DtoPaymentIU;
import com.ferhatayar.service.IPaymentService;

@RestController
@RequestMapping("/rest/api/payment")
public class RestPaymentControllerImpl extends RestBaseController implements IRestPaymentController{

	@Autowired
	private IPaymentService paymentService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoPayment> savePayment(@RequestBody DtoPaymentIU input) {
		return ok(paymentService.savePayment(input));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoPayment>> getAllPaymentList() {
		return ok(paymentService.getAllPaymentList());
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoPayment> getPaymentById(@PathVariable(name = "id") Long id) {
		return ok(paymentService.getPaymentById(id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<DtoPayment> deletePayment(@PathVariable(name = "id") Long id) {
		return ok(paymentService.deletePayment(id));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoPayment> updatePayment(@PathVariable(name = "id") Long id, @RequestBody DtoPaymentIU input) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
