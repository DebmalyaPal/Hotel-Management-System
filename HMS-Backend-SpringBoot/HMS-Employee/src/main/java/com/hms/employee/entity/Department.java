package com.hms.employee.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Department {
	
	@Id
	private String departmentId;
	
	@Column(nullable = false)
	private String departmentName;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Employee> employees = new ArrayList<>();
	
	
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}


	public Department(String departmentId, String departmentName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

}
