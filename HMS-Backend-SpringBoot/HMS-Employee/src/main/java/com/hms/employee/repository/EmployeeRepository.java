package com.hms.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.hms.employee.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
