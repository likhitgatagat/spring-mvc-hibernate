package com.cims.spring.service;

import java.util.List;

import com.cims.spring.entity.User;

public interface UserService {
	public Long addUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	public User getUserById(Long userId);
	public List<User> getAllUsers();
	public Boolean authenticateUser(String userName, String Password);
}
