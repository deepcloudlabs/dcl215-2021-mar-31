package com.example.hr.application;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.hr.application.business.InMemoryEmployeeRepository;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

class HrApplicationTest {

	@ParameterizedTest
	@CsvFileSource(resources = {"/hire-employee.csv"})
	void hireEmployeeSuccessfuly(String csvIdentity,String csvFirstName,String csvLastName,
			String csvIban,double csvMoney,String csvCurrency,String csvDepartment,
			int csvBirthYear,String csvJobType,String csvPhoto) {
		// 1. Test Fixture
		// Test Doubles: Dummy Object
		EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
		EventPublisher eventPublisher = event -> { }; // dummy object
				
		HrApplication hrApplication= new StandardHrApplication(employeeRepository,eventPublisher); 
		var employee = new Employee.Builder(TcKimlikNo.of(csvIdentity))
                .fullname(csvFirstName,csvLastName)
                .iban(csvIban)
                .salary(csvMoney, FiatCurrency.valueOf(csvCurrency))
                .birthYear(csvBirthYear)
                .photo(Base64.getDecoder().decode(csvPhoto.getBytes()))
                .department(csvDepartment)
                .jobType(csvJobType)
                .build();
		// 2. Call Exercise Method
		var success = hrApplication.hireEmployee(employee);
		// 3. Verification
		assertTrue(success);
	}
	
	@ParameterizedTest
	@CsvSource({
		"35373330300,Jack,Bauer,TR440006278659712762534346,100000,TL,IT,1965,FULL_TIME,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"
    })
	void fireEmployeeSuccessfuly(String csvIdentity,String csvFirstName,String csvLastName,
			String csvIban,double csvMoney,String csvCurrency,String csvDepartment,
			int csvBirthYear,String csvJobType,String csvPhoto) {
		// 1. Test Fixture
		// Test Doubles: Dummy Object
		var employee = new Employee.Builder(TcKimlikNo.of(csvIdentity))
                .fullname(csvFirstName,csvLastName)
                .iban(csvIban)
                .salary(csvMoney, FiatCurrency.valueOf(csvCurrency))
                .birthYear(csvBirthYear)
                .photo(Base64.getDecoder().decode(csvPhoto.getBytes()))
                .department(csvDepartment)
                .jobType(csvJobType)
                .build();

		EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
		employeeRepository.save(employee);
		EventPublisher eventPublisher = event -> { }; // dummy object
		HrApplication hrApplication= new StandardHrApplication(employeeRepository,eventPublisher); 
		
		// 2. Call Exercise Method
		var firedEmployee = hrApplication.fireEmployee(TcKimlikNo.of(csvIdentity));
		// 3. Verification
		assertEquals(employee, firedEmployee);
	}
}
