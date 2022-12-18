package com.fastrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.UserException;
import com.fastrack.model.CurrentAdminSession;
import com.fastrack.model.CurrentUserSession;
import com.fastrack.model.User;
import com.fastrack.repository.AdminSessionDao;
import com.fastrack.repository.UserDao;
import com.fastrack.repository.UserSessionDao;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserSessionDao userSessionDao;

	@Autowired
	private AdminSessionDao adminSessionDao;

	@Override
	public User createUser(User user) throws UserException {
		User existingUser = userDao.findByEmail(user.getEmail());
		if (existingUser != null)
			throw new UserException("User already registered with this Email");

		return userDao.save(user);
	}

	@Override
	public User updateUser(User user, String key) throws UserException {
		CurrentUserSession currentUser = userSessionDao.findByUpdateKey(key);

		if (currentUser == null)
			throw new UserException("Please Provide valid User update key");
	

		if (user.getUserId() == currentUser.getUserId()) 

			return userDao.save(user);
		 else
			throw new UserException("Please Provide valid User detailes");

	}

	@Override
	public User deleteUser(Integer userId, String key) throws UserException, AdminException {
		CurrentAdminSession currentAdmin = adminSessionDao.findByUpdateKey(key);

		if (currentAdmin == null) 
			throw new AdminException("Please Provide valid Admin update key");
		
		User user = userDao.findById(userId).orElseThrow(() -> new UserException("Please Provide valid User update key"));
		userDao.delete(user);
		return user;
	}

	@Override
	public User viewUser(Integer userId, String key) throws UserException, AdminException {
		CurrentAdminSession currentAdmin = adminSessionDao.findByUpdateKey(key);

		if (currentAdmin == null) 
			throw new AdminException("Please Provide valid Admin update key");
		

		return userDao.findById(userId).orElseThrow(() -> new UserException("No User found with this "+userId));
		 
	}

	@Override
	public List<User> viewAllUsers(String key) throws UserException, AdminException {
		CurrentAdminSession currentAdmin = adminSessionDao.findByUpdateKey(key);

		if (currentAdmin == null) 
			throw new AdminException("Please Provide valid Admin update key");
		

		List<User> users = userDao.findAll();
		List<User> users2 = userDao.findAll();
		if (users.isEmpty())
			throw new UserException("No User registered in this application");

		return users;
	}

}
