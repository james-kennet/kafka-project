package com.demo.kafka.listener;

import com.demo.kafka.dto.TopicDto;
import com.demo.kafka.service.ExecuteAddressService;
import com.demo.kafka.util.JsonUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class WebsiteListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteListener.class);

	@Autowired
	private ExecuteAddressService executeAddressService;

	@KafkaListener(topics = {"${topic.address.create.website}"},
		groupId = "${group.create.website}",
		containerFactory = "websiteCreateAddressListenerContainerFactory")
	public void websiteListener(ConsumerRecord<String, TopicDto> record, Acknowledgment ack) throws JsonProcessingException {
		LOGGER.info("record offset:{} partition:{}", record.offset(), record.partition());
		LOGGER.info("Website ADDRESS topic: {}", JsonUtility.objectToJson(record.value()));
		try {
			ack.acknowledge();
			LOGGER.info("Website ADDRESS after ack.acknowledge() record.value().getAddress(): {}", record.value().getAddress());
			executeAddressService.callService(record.value().getAddress());
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

}
