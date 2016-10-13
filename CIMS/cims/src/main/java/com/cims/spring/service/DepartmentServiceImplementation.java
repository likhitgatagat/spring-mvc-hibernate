package com.cims.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cims.spring.dao.DepartmentDao;
import com.cims.spring.entity.Department;

@Service
public class DepartmentServiceImplementation implements DepartmentService {

	private DepartmentDao departmentDao;
	
	@Autowired
	public void setUserDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@Override
	@Transactional
	public Integer addDepartment(Department department) {
		return this.departmentDao.addDepartment(department);
	}

	@Override
	@Transactional
	public void updateDepartment(Department department) {
		this.departmentDao.updateDepartment(department);
	}

	@Override
	@Transactional
	public void deleteDepartment(Integer departmentId) {
		this.departmentDao.deleteDepartment(departmentId);

	}

	@Override
	@Transactional
	public Department getDepartmentById(Integer departmentId) {
		return this.departmentDao.getDepartmentById(departmentId);
	}

	@Override
	@Transactional
	public List<Department> getAllDepartments() {
		return this.departmentDao.getAllDepartments();
	}

}
