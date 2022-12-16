package com.fastrack.service;

import java.util.List;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.UserException;
import com.fastrack.model.User;

public interface UserService {
	public User createUser(User user) throws UserException;

	public User updateUser(User user, String key) throws UserException;

	public User deleteUser(Integer userId, String key) throws UserException, AdminException;

	public User viewUser(Integer userId, String key) throws UserException, AdminException;

	public List<User> viewAllUsers(String key) throws UserException, AdminException;

}
