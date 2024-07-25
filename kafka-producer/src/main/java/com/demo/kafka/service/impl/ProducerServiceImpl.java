package com.demo.kafka.service.impl;

import com.demo.kafka.dto.TopicDto;
import com.demo.kafka.dto.TopicDtoUpdate;
import com.demo.kafka.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

	private final KafkaTemplate<String, TopicDto> kafkaTemplate;
	private final KafkaTemplate<String, TopicDtoUpdate> kafkaTemplate1;

	@Override
	public void send(String topic, TopicDto topicDto) {
		kafkaTemplate.send(topic, topicDto);
	}

}
