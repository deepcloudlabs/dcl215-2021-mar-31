package com.example.hr.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.application.HrApplication;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.domain.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HrKafkaController { // Consumer + Producer
	@Autowired
	private HrApplication hrApp;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "hr") // Consumer
	public void listen(String request) throws Exception {
		HireEmployeeRequest hireEmployeeRequest = objectMapper.readValue(request, HireEmployeeRequest.class);
		var employee = modelMapper.map(hireEmployeeRequest, Employee.class);

		HireEmployeeResponse hireEmpResp;
		if (hrApp.hireEmployee(employee)) {
			hireEmpResp = new HireEmployeeResponse("success");
		} else {
			hireEmpResp = new HireEmployeeResponse("failed");
		}

		kafkaTemplate.send("result", objectMapper.writeValueAsString(hireEmpResp));
	}
}
