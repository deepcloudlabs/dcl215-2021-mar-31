package com.example.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String> {
	List<EmployeeEntity> findByBirthYearBetween(int fromYear, int toYear);

	List<EmployeeEntity> findByDepartment(String department);

	@Query("select e from EmployeeEntity e where e.birthYear between :fromYear and :toYear")
	List<EmployeeEntity> yasaGoreGetir(int fromYear, int toYear);

	@Query(value = "select e from employees e where e.birth_year between :fromYear and :toYear", nativeQuery = true)
	List<EmployeeEntity> yasaGoreNativeGetir(int fromYear, int toYear);

}
