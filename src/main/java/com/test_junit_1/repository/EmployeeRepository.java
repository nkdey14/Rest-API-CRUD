package com.test_junit_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test_junit_1.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
