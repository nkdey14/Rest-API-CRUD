package com.test_junit_1.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.test_junit_1.dto.APIResponse;
import com.test_junit_1.dto.EmployeeDto;
import com.test_junit_1.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// http://localhost:9090/api/v1/employees/saveEmployee
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<?> saveEmployeeDetails(@Valid @RequestBody EmployeeDto employeeDto, BindingResult result){
		
		if(result.hasErrors()) {
			
			return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		EmployeeDto empDto = employeeService.saveEmployee(employeeDto);
		
		APIResponse<EmployeeDto> response = new APIResponse<>
											(
												"Employee record saved successfully!!",
												HttpStatus.OK.value(),
												empDto
											);
		
//		Map<String, Object> response = new LinkedHashMap<>();
//		response.put("message", "Employee record saved successfully!!");
//		response.put("data", empDto);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	// http://localhost:9090/api/v1/employees/deleteEmployee/1
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<?> deleteEmployeeDetails(@PathVariable long id){
		
		employeeService.deleteEmployee(id);
		
		APIResponse<Object> response = new APIResponse<>
										(
												"Employee record deleted successfully with id: " + id + " !!",
												HttpStatus.OK.value(),
												null
										);
		
//		Map<String, Object> response = new LinkedHashMap<>();
//		response.put("message", "Employee record deleted successfully with id: " + id + " !!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// http://localhost:9090/api/v1/employees/updateEmployee?id=1
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<?> updateEmployeeDetails(@RequestParam long id, @RequestBody EmployeeDto employeeDto){
		
		EmployeeDto empDto = employeeService.updateEmployee(id, employeeDto);
		
		APIResponse<EmployeeDto> response = new APIResponse<>
											(
												"Employee record updated successfully with id: " + id + " !!",
												HttpStatus.OK.value(),
												empDto
											);
		
//		Map<String, Object> response = new LinkedHashMap<>();
//		response.put("message", "Employee record updated successfully with id: " + id + " !!");
//		response.put("data", empDto);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	// http://localhost:9090/api/v1/employees/listAllEmployees?pageNo=0&pageSize=2&sortBy=name&sortDir=asc
	
	@GetMapping("listAllEmployees")
	public ResponseEntity<?> getAllEmployees(
			@RequestParam(name ="pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(name ="pageSize", defaultValue = "2", required = false) int pageSize,
			@RequestParam(name ="sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name ="sortDir", defaultValue = "asc", required = false) String sortDir
			){
		
		List<EmployeeDto> empDtos = employeeService.findAllEmployees(pageNo, pageSize, sortBy, sortDir);
		
		APIResponse<List<EmployeeDto>> response = new APIResponse<>
											(
												"List of Employees",
												HttpStatus.OK.value(),
												empDtos
											);
		
//		Map<String, Object> response = new LinkedHashMap<>();
//		response.put("message", "List of Employees");
//		response.put("data", empDtos);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// http://localhost:9090/api/v1/employees/getEmployeeById?id=2
	@GetMapping("/getEmployeeById")
	public ResponseEntity<APIResponse<EmployeeDto>> getEmployeeById(@RequestParam long id){
		
		EmployeeDto empDto = employeeService.findEmployeeById(id);
		
		APIResponse<EmployeeDto> response = new APIResponse<>
											(
												"Employee details fetched successfully!!",
												HttpStatus.OK.value(),
												empDto
											);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
