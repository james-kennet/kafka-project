package com.demo.kafka.repository;

import com.demo.kafka.entity.AddressLife400;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressLife400Repository extends JpaRepository<AddressLife400, Integer> {

	Optional<AddressLife400> findByNric(final String nric);
	List<AddressLife400> findAllByOrderByNric();
}
