package com.ferhatayar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferhatayar.model.Purchases;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchases, Long>{

}
