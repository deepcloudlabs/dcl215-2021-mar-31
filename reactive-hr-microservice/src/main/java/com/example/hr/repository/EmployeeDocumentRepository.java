package com.example.hr.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.hr.document.EmployeeDocument;

import reactor.core.publisher.Flux;

public interface EmployeeDocumentRepository extends ReactiveMongoRepository<EmployeeDocument, String> {
	Flux<EmployeeDocument> findByBirthYearBetween(int fromYear, int toYear);

	Flux<EmployeeDocument> findByDepartment(String department);

	@Query("{byear: {$gte: ?0, $lt: ?1}}")
	Flux<EmployeeDocument> yasaGoreGetir(int fromYear, int toYear);
}
