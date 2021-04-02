package com.example.hr;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.fasterxml.jackson.databind.ObjectMapper;

// JUnit 5 -> Extension -> Spring Boot Test -> Spring MVC Test, ...
// Spring MVC Test -> @RestController
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = HrMicroserviceApplication.class)
@AutoConfigureMockMvc
class HrMicroserviceApplicationTests {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private HrService hrService;

	// 1. ProtocolBuffer -> gRpc
	// 2. GraphQL

	// REST -> Document-style (Resource)
	@ParameterizedTest
	@CsvSource({
			"35373330300,Jack,Bauer,TR440006278659712762534346,100000,TL,IT,1965,FULL_TIME,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" })
	void hireEmployeeSuccessfuly(String csvIdentity, String csvFirstName, String csvLastName, String csvIban,
			double csvMoney, String csvCurrency, String csvDepartment, int csvBirthYear, String csvJobType,
			String csvPhoto) throws Throwable {
		// 1. Setup/Fixture
		HireEmployeeRequest request = new HireEmployeeRequest();
		request.setIdentity(csvIdentity);
		request.setFirstName(csvFirstName);
		request.setLastName(csvLastName);
		request.setIban(csvIban);
		request.setSalary(csvMoney);
		request.setCurrency(csvCurrency);
		request.setBirthYear(csvBirthYear);
		request.setJobType(csvJobType);
		request.setDepartment(csvDepartment);
		request.setPhoto(csvPhoto);
		HireEmployeeResponse response = new HireEmployeeResponse("success");
		Mockito.when(hrService.hireEmployee(request)).thenReturn(response);
		// 2. Call exercise method
		var jsonRequest = mapper.writeValueAsString(request);
		mvc.perform(post("/employees").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				// 3. Verification
				.andExpect(status().isOk())
				.andExpect(jsonPath("status", is("success")));
		// 4. Destroy/Teardown Setup
	}

	@ParameterizedTest
	@CsvSource({
	"1111111111,Jack,Bauer,TR1,2000,TL,IT,2010,FULL_TIME,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" })
	void hireEmployeeSendsBadRequest(String csvIdentity, String csvFirstName, String csvLastName, String csvIban,
			double csvMoney, String csvCurrency, String csvDepartment, int csvBirthYear, String csvJobType,
			String csvPhoto) throws Throwable {
		// 1. Setup/Fixture
		HireEmployeeRequest request = new HireEmployeeRequest();
		request.setIdentity(csvIdentity);
		request.setFirstName(csvFirstName);
		request.setLastName(csvLastName);
		request.setIban(csvIban);
		request.setSalary(csvMoney);
		request.setCurrency(csvCurrency);
		request.setBirthYear(csvBirthYear);
		request.setJobType(csvJobType);
		request.setDepartment(csvDepartment);
		request.setPhoto(csvPhoto);
		HireEmployeeResponse response = new HireEmployeeResponse("success");
		Mockito.when(hrService.hireEmployee(request)).thenReturn(response);
		// 2. Call exercise method
		var jsonRequest = mapper.writeValueAsString(request);
		mvc.perform(post("/employees").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
		// 3. Verification
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("status", is("failed")));
		// 4. Destroy/Teardown Setup
	}
	
	@ParameterizedTest
	@CsvSource({
			"35373330300,Jack,Bauer,TR440006278659712762534346,100000,TL,IT,1965,FULL_TIME,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" })
	void fireEmployeeSuccessfuly(String csvIdentity, String csvFirstName, String csvLastName, String csvIban,
			double csvMoney, String csvCurrency, String csvDepartment, int csvBirthYear, String csvJobType,
			String csvPhoto) throws Throwable {
		FireEmployeeResponse response = new FireEmployeeResponse();
		response.setIdentity(csvIdentity);
		response.setFirstName(csvFirstName);
		response.setLastName(csvLastName);
		response.setIban(csvIban);
		response.setSalary(csvMoney);
		response.setCurrency(csvCurrency);
		response.setBirthYear(csvBirthYear);
		response.setJobType(csvJobType);
		response.setDepartment(csvDepartment);
		response.setPhoto(csvPhoto);
		response.setStatus("success");
		Mockito.when(hrService.fireEmployee(csvIdentity)).thenReturn(response);
		mvc.perform(delete("/employees/".concat(csvIdentity)))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("status", is("success")))
		   .andExpect(jsonPath("identity", is(csvIdentity)))
		   .andExpect(jsonPath("firstName", is(csvFirstName)))
		   .andExpect(jsonPath("lastName", is(csvLastName)));
	}
	
	@Test
	void fireEmployeeFailsValidation() throws Throwable {
		mvc.perform(delete("/employees/".concat("11111111111")))
		         .andExpect(status().isBadRequest())
				 .andExpect(jsonPath("status", is("failed")));
	}
}
