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


@SpringBootTest
class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	
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
		
		List<Employee> departmentOneEmployees = new ArrayList<>(Arrays.asList(
							employee1, employee2));
		department1.setEmployees(departmentOneEmployees);
		
		List<Department> departmentList = new ArrayList<>(
								Arrays.asList(department1, department2));
		
		Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);
		Mockito.when(departmentRepository.findById("D1")).thenReturn(
												Optional.of(department1));
	}
		
	@Test
	void testGetAllDepartments() {
		int expectedDepartmentListSize = 2;
		ResponseEntity<List<Department>> responseDepartmentList = 
									departmentService.getAllDepartments();
		int actualDepartmentListSize = responseDepartmentList.getBody().size();
		assertEquals(expectedDepartmentListSize, actualDepartmentListSize);
	}
	
	@Test
	void testGetDepartmentById_Success() {
		String expectedDepartmentId = "D1";
		ResponseEntity<Department> responseDepartment = 
							departmentService.getDepartmentById(expectedDepartmentId);
		String actualDepartmentId = responseDepartment.getBody().getDepartmentId();
		assertEquals(expectedDepartmentId, actualDepartmentId);
	}
	
	@Test
	void testGetDepartmentById_Failure() {
		int expectedResponseStatusCode = ResponseEntity.notFound().build()
													.getStatusCode().value();
		ResponseEntity<Department> responseDepartment = 
							departmentService.getDepartmentById("D3");
		int actualResponseStatusCode = responseDepartment.getStatusCode().value();
		assertEquals(expectedResponseStatusCode, actualResponseStatusCode);
	}

	@Test
	void testGetAllEmployeesByDepartmentId_Success() {
		int expectedEmployeeCount = 2;
		ResponseEntity<List<Employee>> responseEmployeeList = 
								departmentService.getAllEmployeesByDepartmentId("D1");
		int actualEmployeeCount = responseEmployeeList.getBody().size();
		assertEquals(expectedEmployeeCount, actualEmployeeCount);
	}
	
	@Test
	void testGetAllEmployeesByDepartmentId_Failure() {
		int expectedResponseStatusCode = ResponseEntity.notFound().build()
													.getStatusCode().value();
		ResponseEntity<List<Employee>> responseEmployeeList = 
							departmentService.getAllEmployeesByDepartmentId("D3");
		int actualResponseStatusCode = responseEmployeeList.getStatusCode().value();
		assertEquals(expectedResponseStatusCode, actualResponseStatusCode);
	}
}
