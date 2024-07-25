package com.demo.kafka.controller;

import com.demo.kafka.constant.Constants;
import com.demo.kafka.dto.TopicDto;
import com.demo.kafka.dto.TopicDtoUpdate;
import com.demo.kafka.service.ProducerService;
import com.demo.kafka.util.JsonUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AddressProducerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressProducerController.class);

	@Autowired
	private ProducerService objectProducerService;

	@Value("${topic.address.create.salesforce}")
	private String salesforceTopic;

	@Value("${topic.address.create.website}")
	private String websiteTopic;

	@Value("${topic.address.create.life400}")
	private String life400Topic;

	@Value("${topic.address.create}")
	private String allPlatformInOneTopic;

	@PostMapping("/publishSalesForce")
	public ResponseEntity<String> publishSalesforce(@RequestBody TopicDto topicDto) throws JsonProcessingException {
		LOGGER.info("publishSalesForce: Topic :{} to process: {}", topicDto.getTopicName(), JsonUtility.objectToJson(topicDto));
		if(salesforceTopic.equals(topicDto.getTopicName())) {
			objectProducerService.send(salesforceTopic, topicDto);
			return new ResponseEntity<>(Constants.PUBLISHED_SALESFORCE, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Constants.ERROR_INVALID_TOPIC, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/publishWebsite")
	public ResponseEntity<String> publishWebsite(@RequestBody TopicDto topicDto) throws JsonProcessingException {
		LOGGER.info("publishWebsite: Topic :{} to process: {}", topicDto.getTopicName(), JsonUtility.objectToJson(topicDto));
		if(websiteTopic.equals(topicDto.getTopicName())) {
			objectProducerService.send(websiteTopic, topicDto);
			return new ResponseEntity<>(Constants.PUBLISHED_WEBSITE, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Constants.ERROR_INVALID_TOPIC, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/publishLife400")
	public ResponseEntity<String> publishLife400(@RequestBody TopicDto topicDto) throws JsonProcessingException {
		LOGGER.info("publishLife400: Topic :{} to process: {}", topicDto.getTopicName(), JsonUtility.objectToJson(topicDto));
		if(life400Topic.equals(topicDto.getTopicName())) {
			objectProducerService.send(life400Topic, topicDto);
			return new ResponseEntity<>(Constants.PUBLISHED_LIFE400, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Constants.ERROR_INVALID_TOPIC, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/publish-allinone-topic")
	public ResponseEntity<String> publishAllInOneTopic(@RequestBody TopicDto topicDto) throws JsonProcessingException {
		LOGGER.info("publish-allinone-topic: Topic :{} to process: {}", topicDto.getTopicName(), JsonUtility.objectToJson(topicDto));
		if(allPlatformInOneTopic.equals(topicDto.getTopicName())) {
			objectProducerService.send(allPlatformInOneTopic, topicDto);
			return new ResponseEntity<>(Constants.PUBLISHED_ALL_PLATFORM, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Constants.ERROR_INVALID_TOPIC, HttpStatus.BAD_REQUEST);
	}
}
