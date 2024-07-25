package com.demo.kafka.dto;

import lombok.Data;

@Data
public class AddressUpdate extends Address {

	private int id;
	private String status;
}
