package com.ferhatayar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferhatayar.model.Payments;
import com.ferhatayar.model.Purchases;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long>{

	@Transactional
	@Modifying
	@Query("DELETE FROM Payments py WHERE py.purchase = :purchase")
	void deleteAllByPurchase(@Param("purchase") Purchases purchase);
	
}
