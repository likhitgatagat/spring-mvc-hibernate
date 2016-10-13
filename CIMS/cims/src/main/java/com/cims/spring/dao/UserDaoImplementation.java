package com.cims.spring.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cims.spring.entity.User;

@Repository
public class UserDaoImplementation implements UserDao {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Long addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return user.getUserId();
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public void deleteUser(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User)session.load(User.class, userId);
		if(user != null) {
			session.delete(user);
		}
	}

	@Override
	public User getUserById(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, userId);
		user.getFirstName();
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM user u " +
				"WHERE u.isDeleted = 0";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(User.class);
		List<User> result = CommonUtility.castList(User.class, query.list());
		return result;
	}
	
	@Override
	public Boolean authenticateUser(String userName, String Password){
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT count(*) AS count FROM user u " +
				"WHERE u.isDeleted = 0 AND u.userName = ? AND u.password = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("count", LongType.INSTANCE);
		query.setParameter(0, userName);
		query.setParameter(1, Password);
		List<Long> result = CommonUtility.castList(Long.class, query.list());
		return result.get(0) > 0 ? true : false;
	}

}
