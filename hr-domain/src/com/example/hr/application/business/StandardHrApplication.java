package com.example.hr.application.business;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	// dependency -> interface -> loose coupling ✔
	private EmployeeRepository employeeRepository;
	private EventPublisher eventPublisher;

	// dependency injection -> constructor injection ✔
	// spring boot -> spring framework -> dependency injection framework
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public boolean hireEmployee(Employee employee) {
		// business rule
		var identity = employee.getIdentity();
		employeeRepository.save(employee);
		eventPublisher.publishEvent(new EmployeeHiredEvent(identity));
		return true;
	}

	@Override
	public Employee fireEmployee(TcKimlikNo kimlik) {
		var found = employeeRepository.exists(kimlik);
		if (!found)
		   return null;
		var firedEmployee = employeeRepository.findByIdentity(kimlik);
		employeeRepository.remove(firedEmployee);
		return firedEmployee;
		
	}

}
