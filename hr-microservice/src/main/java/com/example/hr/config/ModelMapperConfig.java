package com.example.hr.config;

import java.util.Base64;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.FullName;
import com.example.hr.domain.Money;
import com.example.hr.domain.TcKimlikNo;

@Configuration
public class ModelMapperConfig {
	private static final Converter<HireEmployeeRequest, Employee> hireEmployeeRequest2EmployeeConverter = context -> {
		var hireEmpReq = context.getSource();
		return new Employee.Builder(TcKimlikNo.of(hireEmpReq.getIdentity()))
				.fullname(hireEmpReq.getFirstName(), hireEmpReq.getLastName()).iban(hireEmpReq.getIban())
				.salary(hireEmpReq.getSalary(), FiatCurrency.valueOf(hireEmpReq.getCurrency()))
				.birthYear(hireEmpReq.getBirthYear()).department(hireEmpReq.getDepartment())
				.jobType(hireEmpReq.getJobType()).photo(Base64.getDecoder().decode(hireEmpReq.getPhoto())).build();
	};
	private static final Converter<Employee, FireEmployeeResponse> employee2FireEmployeeResponseConverter = context -> {
		var employee = context.getSource();
		var fireEmpRes = new FireEmployeeResponse();
		FullName fullname = employee.getFullname();
		Money money = employee.getMoney();
		fireEmpRes.setIdentity(employee.getIdentity().getValue());
		fireEmpRes.setFirstName(fullname.getFirstName());
		fireEmpRes.setLastName(fullname.getLastName());
		fireEmpRes.setSalary(money.getValue());
		fireEmpRes.setCurrency(money.getCurrency().name());
		fireEmpRes.setIban(employee.getIban().getValue());
		fireEmpRes.setBirthYear(employee.getBirthYear().getValue());
		fireEmpRes.setDepartment(employee.getDepartment().name());
		fireEmpRes.setJobType(employee.getJobType().name());
		fireEmpRes.setPhoto(new String(employee.getPhoto().getData()));
		return fireEmpRes;
	};

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(hireEmployeeRequest2EmployeeConverter, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(employee2FireEmployeeResponseConverter, Employee.class, FireEmployeeResponse.class);
		return modelMapper;
	}
}
