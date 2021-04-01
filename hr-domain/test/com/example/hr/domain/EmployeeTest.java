package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Testing hr domain's aggregate")
class EmployeeTest {

	@ParameterizedTest
	@CsvFileSource(resources = {"/employees.csv"})
	@DisplayName("Created an aggregate successfully")
	void createEmployee(String csvIdentity,String csvFirstName,String csvLastName,
			String csvIban,double csvMoney,String csvCurrency,String csvDepartment,
			int csvBirthYear,String csvJobType,String csvPhoto) {
		// 1. Test Fixture/Setup
		var employee = new Employee.Builder(TcKimlikNo.of(csvIdentity))
				                   .fullname(csvFirstName,csvLastName)
				                   .iban(csvIban)
				                   .salary(csvMoney, FiatCurrency.valueOf(csvCurrency))
				                   .department(csvDepartment)
				                   .birthYear(csvBirthYear)
				                   .photo(Base64.getDecoder().decode(csvPhoto.getBytes()))
				                   .jobType(csvJobType)
				                   .build();
		// 2. Call Exercise Method
		var identity = employee.getIdentity();
		var fullname = employee.getFullname();
		var iban = employee.getIban();
		var salary = employee.getSalary();
		// 3. Verification
		assertAll(
		   () -> assertEquals(csvIdentity, identity.getValue()),
		   () -> assertEquals(csvFirstName, fullname.getFirstName()),
		   () -> assertEquals(csvLastName, fullname.getLastName()),
		   () -> assertEquals(csvIban, iban.getValue()),
		   () -> assertEquals(csvMoney, salary.getValue()),
		   () -> assertEquals(FiatCurrency.valueOf(csvCurrency), salary.getCurrency()),
		   () -> assertEquals(JobType.valueOf(csvJobType), employee.getJobType()),
		   () -> assertEquals(Department.valueOf(csvDepartment), employee.getDepartment())
		);
		// 4. Tear-down
		// GC
	}

}
