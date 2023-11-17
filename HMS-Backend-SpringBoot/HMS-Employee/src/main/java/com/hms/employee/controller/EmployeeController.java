package com.hms.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.employee.entity.Employee;
import com.hms.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// GET ALL EMPLOYEES
	@GetMapping("/")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	// GET AN EMPLOYEE BY EMPLOYEE ID
	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(
								@PathVariable int employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
	
	// ADD EMPLOYEE TO A DEPARTMENT
	@PostMapping("/department/{departmentId}")
	public ResponseEntity<Employee> addEmployees(
							@PathVariable String departmentId,
							@RequestBody Employee newEmployee) {
		return employeeService.addEmployee(departmentId, newEmployee);
	}
	
	// EDIT EMPLOYEE INFO
	@PutMapping("/{employeeId}")
	public ResponseEntity<Employee> editEmployee(
							@PathVariable int employeeId,
							@RequestBody Employee newEmployee) {
		return employeeService.editEmployee(employeeId, newEmployee);
	}
	
	// DELETE EMPLOYEE
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(
									@PathVariable int employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
	
}
