package com.demo.kafka.controller;

import com.demo.kafka.dto.Address;
import com.demo.kafka.service.AddressService;
import com.demo.kafka.util.JsonUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AddressController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressService addressService;

	@PostMapping
	public void save(@RequestBody Address address) throws JsonProcessingException {
		LOGGER.info("Address clientType:{} to create: {}", address.getClientType(), JsonUtility.objectToJson(address));
		addressService.save(address);
	}

	/** Not part of the current use case. Next version comes with simple UI.
	 *
	@PutMapping("/initiate-update")
	public void initiateUpdate(@RequestBody Address address) {
		LOGGER.info("initiateUpdate > address ={}", address);
		addressService.saveAddressTransaction(address);
	}

	@PutMapping("/post-update")
	public void postUpdate(@RequestBody Address address) {
		LOGGER.info("postUpdate > address ={}", address);
		addressService.saveAddressTransaction(address);
	}

	@PutMapping("/updateAll")
	public void updateAll(@RequestBody Address address) {
		LOGGER.info("updateAll > address ={}", address);
		addressService.updateToAll(address);
	}

	@GetMapping
	public List<Address> get(@RequestHeader("clientType") String clientType) {
		LOGGER.info("fetch address for client ={}", clientType);
		return addressService.getByClientType(clientType);
	}
 **/

}
