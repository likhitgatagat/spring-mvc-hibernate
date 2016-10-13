package com.cims.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cims.spring.dao.RoleDao;
import com.cims.spring.entity.Role;

@Service
public class RoleServiceImplementation implements RoleService {
	
	private RoleDao roleDao;
	
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	@Transactional
	public Integer addRole(Role role) {
		return this.roleDao.addRole(role);
	}

	@Override
	@Transactional
	public void updateRole(Role role) {
		this.roleDao.updateRole(role);
	}

	@Override
	@Transactional
	public void deleteRole(Integer roleId) {
		this.roleDao.deleteRole(roleId);
	}

	@Override
	@Transactional
	public Role getRoleById(Integer roleId) {
		return this.roleDao.getRoleById(roleId);
	}

	@Override
	@Transactional
	public List<Role> getAllRoles() {
		return this.roleDao.getAllRoles();
	}

	@Override
	@Transactional
	public List<Role> getAllRolesForDepartment(Integer departmentId) {
		return this.roleDao.getAllRolesForDepartment(departmentId);
	}

}
