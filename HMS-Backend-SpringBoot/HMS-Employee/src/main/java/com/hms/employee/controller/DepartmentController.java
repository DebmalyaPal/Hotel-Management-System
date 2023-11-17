package com.hms.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.employee.entity.Department;
import com.hms.employee.entity.Employee;
import com.hms.employee.service.DepartmentService;


@RestController
@RequestMapping("/department")
@CrossOrigin("http://localhost:3000")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	// GET ALL DEPARTMENTS
	@GetMapping("/")
	public ResponseEntity<List<Department>> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
	
	//GET A DEPARTMENT USING ID
	@GetMapping("/{departmentId}")
	public ResponseEntity<Department> getDepartmentById(
							@PathVariable String departmentId) {
		return departmentService.getDepartmentById(departmentId);
	}
	
	//GET ALL EMPLOYEES OF A DEPARTMENT USING DEPARTMENT ID
	@GetMapping("/{departmentId}/employees")
	public ResponseEntity<List<Employee>> getAllEmployeesByDepartmentId(
								@PathVariable String departmentId) {
		return departmentService.getAllEmployeesByDepartmentId(departmentId);
	}
	
}

