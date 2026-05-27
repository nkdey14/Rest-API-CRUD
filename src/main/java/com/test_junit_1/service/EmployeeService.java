package com.test_junit_1.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
		
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: "+ id));
		
		emp.setName(employeeDto.getName());
		emp.setEmail(employeeDto.getEmail());
		emp.setMobile(employeeDto.getMobile());
		emp.setCity(employeeDto.getCity());
		
		Employee updatedEmployee = employeeRepository.save(emp);
		
		return mapToEmployeeDto(updatedEmployee);
		
	}
	
	public Employee mapToEmployee(EmployeeDto dto) {
		
		Employee emp = new Employee();
		BeanUtils.copyProperties(dto, emp);
		return emp;
	}
	
	public EmployeeDto mapToEmployeeDto(Employee emp) {
		
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(emp, dto);
		return dto;
	}

	public List<EmployeeDto> findAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc")
	            ? Sort.by(sortBy).ascending()
	            : Sort.by(sortBy).descending();

	    PageRequest page = PageRequest.of(pageNo, pageSize, sort);


	    List<Employee> employees = employeeRepository.findAll(page).getContent();

	    return employees.stream()
	    		.map(this::mapToEmployeeDto)
	    		.collect(Collectors.toList());
	}

	public EmployeeDto findEmployeeById(long id) {
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: "+ id));
		
		return mapToEmployeeDto(employee);
	}
	

}
