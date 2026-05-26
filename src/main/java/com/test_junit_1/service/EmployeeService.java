package com.test_junit_1.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.test_junit_1.dto.EmployeeDto;
import com.test_junit_1.entity.Employee;
import com.test_junit_1.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
		Employee emp = new Employee();
		BeanUtils.copyProperties(employeeDto, emp);
		Employee savedEmployee = employeeRepository.save(emp);
		
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(savedEmployee, dto);
		return dto;
	}

	public void deleteEmployee(long id) {
	    employeeRepository.findById(id)
	    .orElseThrow(() -> new RuntimeException("Employee not found with id: "+ id));
	    employeeRepository.deleteById(id);
	}
	

}
