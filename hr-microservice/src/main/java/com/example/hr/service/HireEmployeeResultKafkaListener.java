package com.example.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.hr.boundary.HireEmployeeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
//@Profile("test")
public class HireEmployeeResultKafkaListener {
	private HireEmployeeResponse response;
	@Autowired
	private ObjectMapper objectMapper;

	@KafkaListener(topics = "result",groupId = "hr-result-test")
	public void listen(String result) throws Exception {
		response = objectMapper.readValue(result, HireEmployeeResponse.class);
	}

	public HireEmployeeResponse getResponse() {
		return response;
	}

}
