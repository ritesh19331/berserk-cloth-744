package com.frs.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frs.exception.LogException;
import com.frs.model.CurrentUserSession;
import com.frs.model.User;
import com.frs.model.UserLoginDTO;
import com.frs.repository.UserDao;
import com.frs.repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginServiceImp implements UserLoginService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public CurrentUserSession logInUser(UserLoginDTO dto) throws LogException {

		User existingUserByEmail = userDao.findByEmail(dto.getEmail());
//		
//		User existingUserByMobile = userDao.findByMobileNumber(dto.getMobileNumber()); 
		

		if (existingUserByEmail == null)
			throw new LogException("No User with this Email Id");
//		if (existingUserByMobile == null)
//			throw new LogException("No User with this  Mobile Number");
		
		Optional<CurrentUserSession> currentUser = userSessionDao.findById(existingUserByEmail.getUserId());

//		Optional<CurrentUserSession> currentUserByMobile = userSessionDao.findById(existingUserByMobile.getUserId());
		
		
		if (currentUser.isPresent()) {

			throw new LogException("User already Logged-In");
		}
		if (existingUserByEmail.getUserPassword().equals(dto.getUserPassword())) {

			String key = RandomString.make(6);

			return userSessionDao.save(new CurrentUserSession(existingUserByEmail.getUserId(), key, LocalDateTime.now()));
		} 
		
//		else if(existingUserByMobile.getUserPassword().equals(dto.getPassword())) {
//			String key = RandomString.make(6);
//
//			return userSessionDao.save(new CurrentUserSession(existingUserByMobile.getUserId(), key, LocalDateTime.now()));
//		
//		}
		
		else
			throw new LogException("Password is wrong "+dto.getUserPassword()+". Enter right Password");
	}

	@Override
	public String logOutUser(String key) throws LogException {

		CurrentUserSession currentUser = userSessionDao.findByUpdateKey(key);

		if (currentUser == null)
			throw new LogException("Without Log In can't perform Log Out.");

		userSessionDao.delete(currentUser);

		return "Log Out Successful";
	}

}
