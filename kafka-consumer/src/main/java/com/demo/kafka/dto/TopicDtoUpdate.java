package com.demo.kafka.dto;

import lombok.Data;

@Data
public class TopicDtoUpdate {

	private String topicName;
	private AddressUpdate address;
}
