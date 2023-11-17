package com.hms.employee.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.hms.employee.entity.Department;
import com.hms.employee.entity.Employee;
import com.hms.employee.repository.DepartmentRepository;
import com.hms.employee.repository.EmployeeRepository;

@SpringBootTest
class EmployeeServiceTest {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	
	@BeforeEach
	void setUp() {
		Department department1 = new Department("D1", "Hotel");
		Department department2 = new Department("D2", "Restaurant");
		Employee employee1 = 
			new Employee(1, "John Cena", "johncena@gmail.com", "9988776655", 50000);
		employee1.setDepartment(department1);
		Employee employee2 = 
			new Employee(2, "Thiago Silva", "thiagosilva@gmail.com", "9977884466", 40000);
		employee2.setDepartment(department1);
		Employee employee3 = 
			new Employee(3, "Lucas Paqueta", "paquetalucas@gmail.com", "8822114477", 30000);
		employee3.setDepartment(department2);
		Employee employee4 = 
			new Employee(4, "Lionel Scaloni", "tacticianscaloni@yahoo.com", "7894563210", 75000);
		employee4.setDepartment(department2);
		
		List<Employee> employeeList = new ArrayList<>(
				Arrays.asList(employee1, employee2, employee3, employee4));
		
		Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(employee1));
		
		Mockito.when(departmentRepository.findById("D1")).thenReturn(
													Optional.of(department1));
	}

	
	@Test
	void testGetAllEmployees() {
		int expectedEmployeeListSize = 4;
		ResponseEntity<List<Employee>> responseEmployeeList = 
								employeeService.getAllEmployees();
		int actualEmployeeListSize = responseEmployeeList.getBody().size();
		assertEquals(expectedEmployeeListSize, actualEmployeeListSize);
	}
	
	@Test
	void testGetEmployeeById_Success() {
		int expectedResponseEmployeeId = 1;
		ResponseEntity<Employee> responseEmployee = 
				employeeService.getEmployeeById(expectedResponseEmployeeId);
		int actualResponseEmployeeId = responseEmployee.getBody().getEmployeeId();
		assertEquals(expectedResponseEmployeeId, actualResponseEmployeeId);
	}
	
	@Test
	void testGetEmployeeById_Failure() {
		int expectedResponseStatusCode = ResponseEntity.notFound().build()
														.getStatusCode().value();
		ResponseEntity<Employee> responseEmployee = employeeService.getEmployeeById(5);
		int actualResponseStatusCode = responseEmployee.getStatusCode().value();
		assertEquals(expectedResponseStatusCode, actualResponseStatusCode);
	}
	
	@Test
	void testAddEmployee_Success() {
		Department department = new Department("D1", "Hotel");
		Employee employee = 
				new Employee(5, "Pablo Aimar", "pablo@gmail.com", "7123456210", 45000);
		employee.setDepartment(department);
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		ResponseEntity<Employee> responseEmployee = 
					employeeService.addEmployee("D1", employee);
		int expectedEmployeeId = employee.getEmployeeId();
		int actualEmployeeId = responseEmployee.getBody().getEmployeeId();
		assertEquals(expectedEmployeeId, actualEmployeeId);
	}
	
	@Test
	void testAddEmployee_Failure() {
		Department department = new Department("D3", "Laundry");
		Employee employee = 
				new Employee(5, "Pablo Aimar", "pablo@gmail.com", "7123456210", 45000);
		employee.setDepartment(department);
		ResponseEntity<Employee> responseEmployee = 
					employeeService.addEmployee("D3", employee);
		int expectedStatusCode = ResponseEntity.notFound().build().getStatusCode().value();
		int actualStatusCode = responseEmployee.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testEditEmployee_Success() {
		Department department = new Department("D1", "Hotel");
		Employee employee = 
				new Employee(1, "John Cena Dp", "johnc@gmail.com", "9988776655", 30000);
		employee.setDepartment(department);
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		ResponseEntity<Employee> responseEmployee = 
					employeeService.editEmployee(1, employee);
		int expectedEmployeeId = employee.getEmployeeId();
		int actualEmployeeId = responseEmployee.getBody().getEmployeeId();
		assertEquals(expectedEmployeeId, actualEmployeeId);
	}
	
	@Test
	void testEditEmployee_Failure() {
		Department department = new Department("D1", "Hotel");
		Employee employee = 
				new Employee(5, "Pablo Aimar", "pablo@gmail.com", "7123456210", 45000);
		employee.setDepartment(department);
		ResponseEntity<Employee> responseEmployee = 
					employeeService.editEmployee(5, employee);
		int expectedStatusCode = ResponseEntity.notFound().build().getStatusCode().value();
		int actualStatusCode = responseEmployee.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testDeleteEmployee_Success() {
		String expectedString = "Employee Deleted Successfully !!";
		ResponseEntity<String> responseMessage = employeeService.deleteEmployee(1);
		String actualString = responseMessage.getBody();
		assertEquals(expectedString, actualString);
	}
	
	@Test
	void testDeleteEmployee_Failure() {
		int expectedResponseStatusCode = ResponseEntity.notFound().build()
														.getStatusCode().value();
		ResponseEntity<String> responseMessage = employeeService.deleteEmployee(5);
		int actualResponseStatusCode = responseMessage.getStatusCode().value();
		assertEquals(expectedResponseStatusCode, actualResponseStatusCode);
	}

}
