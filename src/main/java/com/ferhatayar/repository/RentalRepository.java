package com.ferhatayar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferhatayar.model.Rentals;

@Repository
public interface RentalRepository extends JpaRepository<Rentals, Long>{

}
