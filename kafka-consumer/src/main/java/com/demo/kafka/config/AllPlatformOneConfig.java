package com.demo.kafka.config;

import com.demo.kafka.dto.TopicDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AllPlatformOneConfig {

	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String bootstrapAddress;

	@Value("${group.create.all.platform.salesforce}")
	private String groupAllPlatformSalesforce;

	@Value("${group.create.all.platform.website}")
	private String groupAllPlatformWebsite;

	@Value("${group.create.all.platform.life400}")
	private String groupAllPlatformLife400;

	@Bean
	public ConsumerFactory<String, TopicDto> allPlatformOneSalesforce() {
		Map<String, Object> props = new HashMap<>();
		props.put(
			ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
			bootstrapAddress);
		props.put(
			ConsumerConfig.GROUP_ID_CONFIG,
			groupAllPlatformSalesforce);
		props.put(
			ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
			StringSerializer.class.getName());
		props.put(
			ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
			JsonSerializer.class.getName());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		return new DefaultKafkaConsumerFactory<>(props,
		                                         new StringDeserializer(),
		                                         new JsonDeserializer<>(TopicDto.class));
	}

	@Bean
	public ConsumerFactory<String, TopicDto> allPlatformOneWebsite() {
		Map<String, Object> props = new HashMap<>();
		props.put(
			ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
			bootstrapAddress);
		props.put(
			ConsumerConfig.GROUP_ID_CONFIG,
			groupAllPlatformWebsite);
		props.put(
			ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
			StringSerializer.class.getName());
		props.put(
			ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
			JsonSerializer.class.getName());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		return new DefaultKafkaConsumerFactory<>(props,
		                                         new StringDeserializer(),
		                                         new JsonDeserializer<>(TopicDto.class));
	}

	@Bean
	public ConsumerFactory<String, TopicDto> allPlatformOneLife400() {
		Map<String, Object> props = new HashMap<>();
		props.put(
			ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
			bootstrapAddress);
		props.put(
			ConsumerConfig.GROUP_ID_CONFIG,
			groupAllPlatformLife400);
		props.put(
			ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
			StringSerializer.class.getName());
		props.put(
			ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
			JsonSerializer.class.getName());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		return new DefaultKafkaConsumerFactory<>(props,
		                                         new StringDeserializer(),
		                                         new JsonDeserializer<>(TopicDto.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TopicDto>
	allPlatformSalesforceFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TopicDto> factory =
			new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(allPlatformOneSalesforce());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		factory.setRecordFilterStrategy(
			consumerRecord -> !consumerRecord.value().getAddress().getClientType().equals("SALESFORCE")
		);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TopicDto>
	allPlatformWebsiteFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TopicDto> factory =
			new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(allPlatformOneWebsite());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		factory.setRecordFilterStrategy(
			consumerRecord -> !consumerRecord.value().getAddress().getClientType().equals("WEBSITE")
		);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TopicDto>
	allPlatformLife400Factory() {
		ConcurrentKafkaListenerContainerFactory<String, TopicDto> factory =
			new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(allPlatformOneLife400());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		factory.setRecordFilterStrategy(
			consumerRecord -> !consumerRecord.value().getAddress().getClientType().equals("LIFE400")
		);
		return factory;
	}

}
