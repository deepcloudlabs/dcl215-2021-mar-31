package com.example.hr.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Service
@ConditionalOnProperty(name = "persistence.target", havingValue = "mongo")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	@Autowired
	private EmployeeDocumentRepository empDocRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Employee save(Employee employee) {
		var empDoc = modelMapper.map(employee, EmployeeDocument.class);
		empDoc = empDocRepo.save(empDoc);
		return modelMapper.map(empDoc, Employee.class);
	}

	@Override
	public boolean exists(TcKimlikNo kimlik) {
		return empDocRepo.existsById(kimlik.getValue());
	}

	@Override
	public Employee findByIdentity(TcKimlikNo kimlik) {
		return modelMapper.map(empDocRepo.findById(kimlik.getValue()), Employee.class);
	}

	@Override
	public void remove(Employee firedEmployee) {
		var identity = firedEmployee.getIdentity().getValue();
		var empDoc = empDocRepo.findById(identity);
		if (empDoc.isEmpty())
			throw new IllegalArgumentException("Cannot find employee to delete");
		empDocRepo.delete(empDoc.get());
	}

}
