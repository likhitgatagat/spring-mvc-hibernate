package com.cims.spring.dao;

import java.util.List;

import com.cims.spring.entity.Department;

public interface DepartmentDao {
	public Integer addDepartment(Department department);
	public void updateDepartment(Department department);
	public void deleteDepartment(Integer departmentId);
	public Department getDepartmentById(Integer departmentId);
	public List<Department> getAllDepartments();
}
