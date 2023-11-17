package com.hms.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.employee.entity.Department;
import com.hms.employee.entity.Employee;
import com.hms.employee.repository.DepartmentRepository;
import com.hms.employee.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	// Get All Employees
	public ResponseEntity<List<Employee>> getAllEmployees() {
		System.out.println("FETCHING LIST OF ALL EMPLOYEES");
		List<Employee> employeeList = 
				(List<Employee>)employeeRepository.findAll();
		System.out.println("ACTION SUCCESSFUL -> No. Of Employees - "
				+ employeeList.size());
		return ResponseEntity.ok().body(employeeList);
	}
	
	// Get Employee by Employee ID
	public ResponseEntity<Employee> getEmployeeById(int employeeId) {
		System.out.println("FETCHING EMPLOYEE WITH ID - " + employeeId);
		Optional<Employee> optionalEmployee = 
				employeeRepository.findById(employeeId);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			System.out.println("EMPLOYEE FETCHED SUCCESSFULLY with ID - " 
											+ employeeId);
			return ResponseEntity.ok().body(employee);
		}
		else {
			System.out.println("NO EMPLOYEE FOUND WITH ID - " + employeeId);
			return ResponseEntity.notFound().build();
		}
	}
	
	// Add a Employee
	public ResponseEntity<Employee> addEmployee(
					String departmentId, Employee newEmployee) {
		System.out.println("ADDING AN EMPLOYEE TO DEPARTMENT WITH ID - " 
										+ departmentId);
		Optional<Department> optionalDepartment = 
				departmentRepository.findById(departmentId);
		if (optionalDepartment.isPresent()) {
			Department department = optionalDepartment.get();
			department.addEmployee(newEmployee);
			newEmployee.setDepartment(department);
			employeeRepository.save(newEmployee);
			System.out.println("EMPLOYEE SUCCESSFULLY ADDED TO DEPARTMENT " 
								+ "WITH ID - " + departmentId);
			return ResponseEntity.ok().body(newEmployee);
		}
		else {
			System.out.println("EMPLOYEE COULD NOT BE ADDED TO DEPARTMENT "
								+ "WITH ID - " + departmentId);
			return ResponseEntity.notFound().build();
		}
	}
	
	// Edit an Employee's Info
	public ResponseEntity<Employee> editEmployee(
					int employeeId, Employee updatedEmployee) {
		System.out.println("UPDATING EMPLOYEE WITH ID - " + employeeId);
		Optional<Employee> optionalEmployee = 
				employeeRepository.findById(employeeId);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employee.setName(updatedEmployee.getName());
			employee.setEmail(updatedEmployee.getEmail());
			employee.setPhone(updatedEmployee.getPhone());
			employee.setSalary(updatedEmployee.getSalary());
			employeeRepository.save(employee);
			System.out.println("EMPLOYEE with ID = " + employeeId
								+ " SUCCESSFULLY UPDATED");
			return ResponseEntity.ok().body(employee);
		}
		else {
			System.out.println("EMPLOYEE with ID = " + employeeId + " NOT FOUND");
			return ResponseEntity.notFound().build();
		}
	}
	
	// Delete an Employee
	public ResponseEntity<String> deleteEmployee(int employeeId) {
		System.out.println("DELETING EMPLOYEE WITH ID - "+ employeeId);
		Optional<Employee> optionalEmployee = 
				employeeRepository.findById(employeeId);
		if (optionalEmployee.isPresent()) {
			employeeRepository.deleteById(employeeId);
			System.out.println("EMPLOYEE WITH ID - " + employeeId 
								+ " DELETED SUCCESSFULLY");
			return ResponseEntity.ok()
								.body("Employee Deleted Successfully !!");
		}
		else {
			System.out.println("EMPLOYEE WITH ID - " + employeeId + " NOT FOUND");
			return ResponseEntity.notFound().build();
		}
	}

}
