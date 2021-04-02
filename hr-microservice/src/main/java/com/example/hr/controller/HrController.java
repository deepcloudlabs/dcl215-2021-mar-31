package com.example.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.hr.validation.TcKimlikNo;

@RestController
@RequestMapping("employees")
@RequestScope
@CrossOrigin
// Adapter -> GoF Adapter Pattern
//  Http -> Class Method -> HrApplication
//  Employee -> Aggregate (DDD) -> ACL (Anti-Corruption Layer -> DDD) -> REST (Resource)
//                          hireEmployee
//                          fireEmployee
@Validated
public class HrController { 
	@Autowired
	private HrService hrService;
	
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee( @Validated @PathVariable @TcKimlikNo String identity) {
		return hrService.fireEmployee(identity);
	}
	
}
