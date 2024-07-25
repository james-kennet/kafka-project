package com.demo.kafka.config;

import com.demo.kafka.dto.TopicDto;
import com.demo.kafka.dto.TopicDtoUpdate;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AddressProducerConfig {
	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapAddress;

	@Bean
	public ProducerFactory<String, TopicDto> producerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
			bootstrapAddress);
		props.put(
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
			StringSerializer.class.getName());
		props.put(
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
			JsonSerializer.class.getName());
		return new DefaultKafkaProducerFactory<String, TopicDto>(props);
	}

	@Bean
	public ProducerFactory<String, TopicDtoUpdate> producerFactory1() {
		Map<String, Object> props = new HashMap<>();
		props.put(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
			bootstrapAddress);
		props.put(
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
			StringSerializer.class.getName());
		props.put(
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
			JsonSerializer.class.getName());
		return new DefaultKafkaProducerFactory<String, TopicDtoUpdate>(props);
	}

	@Bean
	public KafkaTemplate<String, TopicDto>
	kafkaTemplate() {
		return new KafkaTemplate<String, TopicDto>(producerFactory());
	}

	@Bean
	public KafkaTemplate<String, TopicDtoUpdate>
	kafkaTemplate1() {
		return new KafkaTemplate<String, TopicDtoUpdate>(producerFactory1());
	}

}
