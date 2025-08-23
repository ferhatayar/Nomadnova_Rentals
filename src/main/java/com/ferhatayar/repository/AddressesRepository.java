package com.ferhatayar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferhatayar.model.Addresses;

@Repository
public interface AddressesRepository extends JpaRepository<Addresses, Long>{

}
