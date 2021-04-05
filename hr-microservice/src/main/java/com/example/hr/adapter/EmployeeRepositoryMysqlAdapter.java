package com.example.hr.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Component
@ConditionalOnProperty(name = "persistence.target", havingValue = "mysql")
public class EmployeeRepositoryMysqlAdapter implements EmployeeRepository {
	@Autowired
	private EmployeeEntityRepository empRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	@Transactional
	public Employee save(Employee employee) {
		var entity = mapper.map(employee,EmployeeEntity.class);
		entity = empRepo.save(entity);
		return mapper.map(entity,Employee.class);
	}

	@Override
	public boolean exists(TcKimlikNo kimlik) {
		return empRepo.existsById(kimlik.getValue());
	}

	@Override
	public Employee findByIdentity(TcKimlikNo kimlik) {
		var entity = empRepo.findById(kimlik.getValue());
		var employee = mapper.map(entity , Employee.class);
		return employee ;
	}

	@Override
	@Transactional
	public void remove(Employee employee) {
		var kimlik = employee.getIdentity();
		var managedEmployee = empRepo.findById(kimlik .getValue());
		if(managedEmployee.isEmpty())
			throw new IllegalArgumentException("Cannot find employee");
		empRepo.delete(managedEmployee.get());
	}

}
