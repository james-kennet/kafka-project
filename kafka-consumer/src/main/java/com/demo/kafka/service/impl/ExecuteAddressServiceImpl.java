package com.demo.kafka.service.impl;

import com.demo.kafka.dto.Address;
import com.demo.kafka.dto.AddressUpdate;
import com.demo.kafka.service.ExecuteAddressService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExecuteAddressServiceImpl implements ExecuteAddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteAddressServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${address.service.endpoint}")
	private String addressEndpoint;

	@Override
	public void callService(Address address) {
		try {
			ResponseEntity<String> result = restTemplate.postForEntity(addressEndpoint, address, String.class);
			LOGGER.info("API call result: {}", result);
		} catch (Exception e) {
			LOGGER.error("Error in callService to create an address: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public void updateToAllService(AddressUpdate address) {
		try {
			restTemplate.put(addressEndpoint + "/updateAll", address);
		} catch (Exception e) {
			LOGGER.error("Error in callService to create an address: {}", e.getMessage());
			throw e;
		}
	}
}
