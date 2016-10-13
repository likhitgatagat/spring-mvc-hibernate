package com.cims.spring.dao;

import java.util.List;

import com.cims.spring.entity.Role;

public interface RoleDao {
	public Integer addRole(Role role);
	public void updateRole(Role role);
	public void deleteRole(Integer roleId);
	public Role getRoleById(Integer roleId);
	public List<Role> getAllRoles();
	public List<Role> getAllRolesForDepartment(Integer departmentId);
}
