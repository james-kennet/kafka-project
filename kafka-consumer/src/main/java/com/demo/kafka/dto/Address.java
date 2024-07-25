package com.demo.kafka.dto;

import lombok.Data;

@Data
public class Address {

	private String nric;
	private String unitNo;
	private String streetName;
	private String country;
	private String postalCode;
	private String clientType;

}
