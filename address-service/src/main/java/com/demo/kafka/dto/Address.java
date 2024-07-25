package com.demo.kafka.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

	private int id;
	private String nric;
	private String unitNo;
	private String streetName;
	private String country;
	private String postalCode;
	private String clientType;
	private String status;

}
