package com.example.hr.application.business;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

public class InMemoryEmployeeRepository implements EmployeeRepository {
	private Map<String, Employee> employees = new ConcurrentHashMap<>();

	@Override
	public Employee save(Employee employee) {
		String identity = employee.getIdentity().getValue();
		employees.putIfAbsent(identity, employee);
		return employee;
	}

	@Override
	public boolean exists(TcKimlikNo kimlik) {
		return Objects.nonNull(employees.get(kimlik.getValue()));
	}

	@Override
	public Employee findByIdentity(TcKimlikNo kimlik) {
		return employees.get(kimlik.getValue());
	}

	@Override
	public void remove(Employee firedEmployee) {
		employees.remove(firedEmployee.getIdentity().getValue());	
	}

}
