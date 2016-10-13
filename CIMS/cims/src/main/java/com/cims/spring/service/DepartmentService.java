package com.cims.spring.service;

import java.util.List;

import com.cims.spring.entity.Department;

public interface DepartmentService {
	public Integer addDepartment(Department department);
	public void updateDepartment(Department department);
	public void deleteDepartment(Integer departmentId);
	public Department getDepartmentById(Integer departmentId);
	public List<Department> getAllDepartments();
}
