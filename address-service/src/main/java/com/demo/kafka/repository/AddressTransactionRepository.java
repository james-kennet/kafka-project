package com.demo.kafka.repository;

import com.demo.kafka.entity.AddressTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressTransactionRepository extends JpaRepository<AddressTransaction, Integer> {

}
