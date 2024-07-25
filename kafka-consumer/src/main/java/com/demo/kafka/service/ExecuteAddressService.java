package com.demo.kafka.service;

import com.demo.kafka.dto.Address;
import com.demo.kafka.dto.AddressUpdate;

public interface ExecuteAddressService {

	void callService(Address address);

	void updateToAllService(AddressUpdate address);
}
