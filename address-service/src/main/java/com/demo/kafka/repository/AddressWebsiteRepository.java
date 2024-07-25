package com.demo.kafka.repository;

import com.demo.kafka.entity.AddressWebsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressWebsiteRepository extends JpaRepository<AddressWebsite, Integer> {

	Optional<AddressWebsite> findByNric(final String nric);
}
