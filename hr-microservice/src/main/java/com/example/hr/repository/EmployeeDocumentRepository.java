package com.example.hr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.hr.document.EmployeeDocument;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String> {
	List<EmployeeDocument> findByBirthYearBetween(int fromYear, int toYear);

	List<EmployeeDocument> findByDepartment(String department);

	@Query("{byear: {$gte: ?0, $lt: ?1}}")
	List<EmployeeDocument> yasaGoreGetir(int fromYear, int toYear);
}
