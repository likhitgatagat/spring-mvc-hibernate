package com.cims.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cims.spring.entity.Department;
import com.cims.spring.entity.Role;
import com.cims.spring.entity.User;
import com.cims.spring.service.DepartmentService;
import com.cims.spring.service.RoleService;
import com.cims.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String listUsers(Model model, @ModelAttribute(value="user") User user) {
		Map<String, String> departments = new HashMap<String, String>();
		List<Department> departmentList = departmentService.getAllDepartments();
		for (Department dept: departmentList) {
			departments.put(dept.getDepartmentId().toString(), dept.getDepartmentName());
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("departments", departments);
		return "user";
	}
	
	@RequestMapping(value="/user/add", method=RequestMethod.POST)
	public String addUser(@ModelAttribute(value="user") User user) {
		if(user.getUserId() == null) {
			userService.addUser(user);
		}
		else {
			userService.updateUser(user);
		}
		return "redirect:/user";
	}
	
	@RequestMapping(value="user/delete/{userId}")
	public String deleteUser(@PathVariable("userId") long userId) {
		User user = userService.getUserById(userId);
		userService.deleteUser(user);
		return "redirect:/user";
	}
	
	@RequestMapping(value= {"ajax/role", "user/edit/{userId}/roles"}, method=RequestMethod.POST)
	@ResponseBody
	public String getRoles(@RequestParam("deptId") String departmentId) {
		StringBuilder roles = new StringBuilder("{");
		List<Role> roleList = roleService.getAllRolesForDepartment(Integer.parseInt(departmentId));
		for (Role role: roleList) {
			roles.append("\"" + role.getRoleId().toString() + "\":" + "\"" + role.getRoleName() + "\",");
		}
		roles.deleteCharAt(roles.length() - 1);
		roles.append("}");
		System.out.println(roles.toString());
	    return roles.toString();
	}
	
	@RequestMapping(value="user/edit/{userId}")
	public String editUser(@PathVariable("userId") long userId, Model model) {
		User user = userService.getUserById(userId);
		Map<String, String> departments = new HashMap<String, String>();
		List<Department> departmentList = departmentService.getAllDepartments();
		for (Department dept: departmentList) {
			departments.put(dept.getDepartmentId().toString(), dept.getDepartmentName());
		}
		Map<String, String> roles = new HashMap<String, String>();
		List<Role> roleList = roleService.getAllRolesForDepartment(user.getDepartmentId());
		for (Role rl: roleList) {
			roles.put(rl.getRoleId().toString(), rl.getRoleName());
		}
		model.addAttribute("user", user);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("departments", departments);
		model.addAttribute("roles", roles);
		return "user";
	}

}
