package com.demo.kafka.service;

import com.demo.kafka.dto.Address;

import java.util.List;

public interface AddressService {

	void save(Address address);

	void updateToAll(final Address address);

	void saveAddressTransaction(final Address address);

	List<Address> getByClientType(String clientType);
}
