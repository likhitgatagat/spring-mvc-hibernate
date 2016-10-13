package com.cims.spring.service;

import java.util.List;

import com.cims.spring.entity.Role;

public interface RoleService {
	public Integer addRole(Role role);
	public void updateRole(Role role);
	public void deleteRole(Integer roleId);
	public Role getRoleById(Integer roleId);
	public List<Role> getAllRoles();
	public List<Role> getAllRolesForDepartment(Integer departmentId);
}
