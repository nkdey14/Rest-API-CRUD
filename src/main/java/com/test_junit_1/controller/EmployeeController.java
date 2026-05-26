package com.test_junit_1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test_junit_1.dto.EmployeeDto;
import com.test_junit_1.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// http://localhost:9090/api/v1/employees/saveEmployee
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<?> saveEmployeeDetails(@RequestBody EmployeeDto employeeDto){
		
		EmployeeDto empDto = employeeService.saveEmployee(employeeDto);
		
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Employee record saved successfully!!");
		response.put("data", empDto);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
