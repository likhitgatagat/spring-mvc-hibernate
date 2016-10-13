package com.cims.spring.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cims.spring.entity.Role;

@Repository
public class RoleDaoImplementation implements RoleDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer addRole(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(role);
		return role.getRoleId();
	}

	@Override
	public void updateRole(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(role);
	}

	@Override
	public void deleteRole(Integer roleId) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = (Role)session.load(Role.class, roleId);
		session.delete(role);
	}

	@Override
	public Role getRoleById(Integer roleId) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = (Role)session.load(Role.class, roleId);
		return role;
	}

	@Override
	public List<Role> getAllRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roleList = CommonUtility.castList(Role.class, session.createQuery("FROM Role").list());
		return roleList;
	}

	@Override
	public List<Role> getAllRolesForDepartment(Integer departmentId) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "SELECT r.* FROM roledepartment rd " +
				"JOIN role r ON r.roleId = rd.roleId and r.isDeleted = 0 " +
				"JOIN department d ON d.departmentId = rd.departmentId and d.isDeleted = 0 " +
				"WHERE rd.departmentId = ? and rd.isDeleted = 0";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Role.class);
		query.setParameter(0, departmentId);
		List<Role> result = CommonUtility.castList(Role.class, query.list());
		return result;
	}

}
