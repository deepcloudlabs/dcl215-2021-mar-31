package com.example.hr.adapter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infra.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "message.bus", havingValue = "rabbitmq")
public class EventPublisherRabbitAdapter implements EventPublisher {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void publishEvent(EmployeeHiredEvent event) {
		try {
			var json = objectMapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend("hr-exchange", null, json);
		} catch (JsonProcessingException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

}
