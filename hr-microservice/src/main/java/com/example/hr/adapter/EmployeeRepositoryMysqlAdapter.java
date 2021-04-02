package com.example.hr.adapter;

import org.springframework.stereotype.Component;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

@Component
public class EmployeeRepositoryMysqlAdapter implements EmployeeRepository {

	@Override
	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(TcKimlikNo kimlik) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee findByIdentity(TcKimlikNo kimlik) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Employee firedEmployee) {
		// TODO Auto-generated method stub

	}

}
