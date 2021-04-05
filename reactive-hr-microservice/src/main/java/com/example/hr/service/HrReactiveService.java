package com.example.hr.service;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.repository.EmployeeDocumentRepository;

import reactor.core.publisher.Mono;

@Service
public class HrReactiveService {

	@Autowired
	private EmployeeDocumentRepository empDocRepo;
	@Autowired
	private ModelMapper mapper;

	public Mono<HireEmployeeResponse> hireEmployee(HireEmployeeRequest request) {
		return empDocRepo.save(mapper.map(request, EmployeeDocument.class))
				         .map(emp -> mapper.map(emp, HireEmployeeResponse.class));
	}

	public Mono<FireEmployeeResponse> fireEmployee(String identity) {
		
		return empDocRepo.findById(identity)
				         .switchIfEmpty(Mono.empty())
				         .filter(Objects::nonNull)
				         .flatMap(
				             empDocToDelete -> empDocRepo.delete(empDocToDelete)
				        	                             .then(Mono.just(mapper.map(empDocToDelete, FireEmployeeResponse.class)))
				          );
	}

}
