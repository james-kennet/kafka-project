package com.demo.kafka.repository;

import com.demo.kafka.entity.AddressSalesforce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressSalesforceRepository extends JpaRepository<AddressSalesforce, Integer> {

	Optional<AddressSalesforce> findByNric(final String nric);
}
