package com.cims.spring.dao;

import java.util.List;
import com.cims.spring.entity.User;

public interface UserDao {
	public Long addUser(User user);
	public void updateUser(User user);
	public void deleteUser(Long userId);
	public User getUserById(Long userId);
	public List<User> getAllUsers();
	public Boolean authenticateUser(String userName, String Password);
}
