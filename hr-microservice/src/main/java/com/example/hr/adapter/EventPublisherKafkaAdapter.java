package com.example.hr.adapter;

import org.springframework.stereotype.Service;

import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infra.EventPublisher;

@Service
public class EventPublisherKafkaAdapter implements EventPublisher {

	@Override
	public void publishEvent(EmployeeHiredEvent employeeHiredEvent) {
		// TODO Auto-generated method stub

	}

}
