package com.example.hr.application;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface HrApplication {

	boolean hireEmployee(Employee employee);

	Employee fireEmployee(TcKimlikNo of);

}
