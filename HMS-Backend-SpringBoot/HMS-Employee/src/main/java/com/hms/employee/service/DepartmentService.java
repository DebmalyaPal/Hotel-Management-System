package com.hms.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.employee.entity.Department;
import com.hms.employee.entity.Employee;
import com.hms.employee.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// Get All Departments
	public ResponseEntity<List<Department>> getAllDepartments() {
		System.out.println("FETCHING LIST OF ALL DEPARTMENTS");
		List<Department> departmentList = 
				(List<Department>)departmentRepository.findAll();
		System.out.println("ACTION SUCCESSFUL -> No. Of Departments - "
										+ departmentList.size());
		return ResponseEntity.ok().body(departmentList);
	}
	
	// Get A Department By Department's ID
	public ResponseEntity<Department> getDepartmentById(String departmentId) {
		System.out.println("FETCHING DEPARTMENT WITH ID - " + departmentId);
		Optional<Department> optionalDepartment = 
				departmentRepository.findById(departmentId);
		if (optionalDepartment.isPresent()) {
			Department department = optionalDepartment.get();
			System.out.println("DEPARTMENT FETCHED SUCCESSFULLY with ID - " 
										+ departmentId);
			return ResponseEntity.ok().body(department);
		}
		else {
			System.out.println("NO DEPARTMENT FOUND WITH ID - " + departmentId);
			return ResponseEntity.notFound().build();
		}
	}
	
	// Get All Employees of a Department by Department's ID
	public ResponseEntity<List<Employee>> getAllEmployeesByDepartmentId(
												String departmentId) {
		System.out.println("FETCHING LIST OF ALL EMPLOYEES OF DEPARTMENT WITH ID - "
								+ departmentId);
		Optional<Department> optionalDepartment = 
					departmentRepository.findById(departmentId);
		if (optionalDepartment.isPresent()) {
			Department department = optionalDepartment.get();
			List<Employee> employeeList = department.getEmployees();
			System.out.println("EMPLOYEES OF DEPARTMENT FETCHED SUCCESSFULLY -> "
								+ "Number of Employees = " + employeeList.size());
			return ResponseEntity.ok().body(employeeList);
		}
		else {
			System.out.println("NO DEPARTMENT FOUND WITH ID - " + departmentId);
			return ResponseEntity.notFound().build();
		}
	}
	
}
