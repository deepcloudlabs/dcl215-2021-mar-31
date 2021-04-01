package com.example.hr.infra;

import com.example.hr.events.EmployeeHiredEvent;

@FunctionalInterface
public interface EventPublisher {

	void publishEvent(EmployeeHiredEvent employeeHiredEvent);
}
