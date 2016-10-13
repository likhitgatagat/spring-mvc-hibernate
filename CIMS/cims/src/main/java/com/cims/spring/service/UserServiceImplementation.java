package com.cims.spring.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cims.spring.dao.UserDao;
import com.cims.spring.entity.User;

@Service
public class UserServiceImplementation implements UserService {

	private UserDao userDao;
	SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy:MM;dd hh:mm:ss");
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	@Transactional
	public Long addUser(User user) {
		user.setCreatedDate(dtFormat.format(new Date()));
		user.setCreatedBy(Long.parseLong("1"));
		user.setIsDeleted(0);
		return this.userDao.addUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		user.setUpdatedDate(dtFormat.format(new Date()));
		user.setUpdatedBy(Long.parseLong("1"));
		user.setIsDeleted(0);
		this.userDao.updateUser(user);

	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		user.setIsDeleted(1);
		this.userDao.updateUser(user);
	}

	@Override
	@Transactional
	public User getUserById(Long userId) {
		return this.userDao.getUserById(userId);
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return this.userDao.getAllUsers();
	}
	
	@Override
	@Transactional
	public Boolean authenticateUser(String userName, String Password){
		return this.userDao.authenticateUser(userName, Password);
	}

}
