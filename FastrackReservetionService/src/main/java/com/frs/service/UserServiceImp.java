package com.frs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frs.exception.AdminException;
import com.frs.exception.UserException;
import com.frs.model.CurrentAdminSession;
import com.frs.model.CurrentUserSession;
import com.frs.model.User;
import com.frs.repository.AdminSessionDao;
import com.frs.repository.UserDao;
import com.frs.repository.UserSessionDao;

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
		User existingUserByEmail = userDao.findByEmail(user.getEmail());
		User existingUserByMobileNumber = userDao.findByMobileNumber(user.getMobileNumber());

		if (existingUserByEmail != null)
			throw new UserException("An User already registered with this Email");
		else if (existingUserByMobileNumber != null)
			throw new UserException("An User already registered with this Mobile Number");

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
		if (users.isEmpty())
			throw new UserException("No User registered in this application");

		return users;
	}

}
