package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hr.application.HrApplication;
import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Service
public class HrService {
	@Autowired
	private HrApplication hrApplication;
	@Autowired
	private ModelMapper mapper;
	
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee  = mapper.map(request,Employee.class);
		hrApplication.hireEmployee(employee);
		return new HireEmployeeResponse("success");
	}

	public FireEmployeeResponse fireEmployee(String identity) {
		var employee = hrApplication.fireEmployee(TcKimlikNo.of(identity));
		return mapper.map(employee, FireEmployeeResponse.class);
	}

}
