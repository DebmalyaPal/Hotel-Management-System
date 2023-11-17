package com.hms.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.hms.employee.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {

}
