package com.cims.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cims.spring.entity.Department;

@Repository
public class DepartmentDaoImplementation implements DepartmentDao {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer addDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();
		session.save(department);
		return department.getDepartmentId();
	}

	@Override
	public void updateDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();
		session.update(department);

	}

	@Override
	public void deleteDepartment(Integer departmentId) {
		Session session = sessionFactory.getCurrentSession();
		Department department = (Department)session.load(Department.class, departmentId);
		session.delete(department);
	}

	@Override
	public Department getDepartmentById(Integer departmentId) {
		Session session = sessionFactory.getCurrentSession();
		Department department = (Department)session.load(Department.class, departmentId);
		return department;
	}

	@Override
	public List<Department> getAllDepartments() {
		Session session = sessionFactory.getCurrentSession();
		List<Department> departmentList = CommonUtility.castList(Department.class, session.createQuery("From Department").list());
		return departmentList;
	}

}
