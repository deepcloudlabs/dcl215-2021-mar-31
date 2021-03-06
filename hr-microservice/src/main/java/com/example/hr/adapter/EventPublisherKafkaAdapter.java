package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infra.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "message.bus", havingValue = "kafka")
public class EventPublisherKafkaAdapter implements EventPublisher {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void publishEvent(EmployeeHiredEvent event) {
		try {
			var json = objectMapper.writeValueAsString(event);
			kafkaTemplate.send("events", json);
		} catch (JsonProcessingException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

}
