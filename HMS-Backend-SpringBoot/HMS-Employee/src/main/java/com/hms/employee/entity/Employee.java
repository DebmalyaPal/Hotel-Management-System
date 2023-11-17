package com.hms.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int employeeId;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name = "departmentId")
	private Department department;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private int salary;

	
	public Employee(String name, 
					Department department, 
					String email, 
					String phone, 
					int salary) {
		this.name = name;
		this.department = department;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
	}
	
	public Employee(int id,
				String name, 
				String email, 
				String phone, 
				int salary) {
		this.employeeId=id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
	}
	
}
