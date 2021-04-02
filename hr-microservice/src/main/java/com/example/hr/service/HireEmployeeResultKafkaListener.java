package com.example.hr.service;

import java.util.concurrent.CyclicBarrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.hr.boundary.HireEmployeeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Profile("test")
public class HireEmployeeResultKafkaListener {
	private CyclicBarrier barrier = new CyclicBarrier(2);
	private HireEmployeeResponse response = new HireEmployeeResponse("");
	@Autowired
	private ObjectMapper objectMapper;

	@KafkaListener(topics = "result")
	public void listen(String result) throws Exception {
		response = objectMapper.readValue(result, HireEmployeeResponse.class);
		barrier.await();
	}

	public HireEmployeeResponse getResponse() {
		return response;
	}

	public CyclicBarrier getBarrier() {
		return barrier;
	}

}
