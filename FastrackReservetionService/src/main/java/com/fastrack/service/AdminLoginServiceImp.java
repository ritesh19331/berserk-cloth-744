package com.fastrack.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.LogException;
import com.fastrack.model.Admin;
import com.fastrack.model.AdminLoginDTO;
import com.fastrack.model.CurrentAdminSession;
import com.fastrack.repository.AdminDao;
import com.fastrack.repository.AdminSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImp implements AdminLoginService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private AdminSessionDao adminSessionDao;

	@Override
	public CurrentAdminSession logInAdmin(AdminLoginDTO dto) throws LogException {

		Admin existingAdmin = adminDao.findByEmail(dto.getEmail());

		if (existingAdmin == null)
			throw new LogException("No admin with this Email Id");
		
		Optional<CurrentAdminSession> currentAdminOpt = adminSessionDao.findById(existingAdmin.getAdminId());

		if (currentAdminOpt.isPresent()) 

			throw new LogException("Admin already Logged-In");
		
		if (existingAdmin.getAdminPassword().equals(dto.getAdminPassword())) {

			String key = RandomString.make(6);

			return adminSessionDao.save(new CurrentAdminSession(existingAdmin.getAdminId(), key, LocalDateTime.now()));
		} else
			throw new LogException("Password is wrong");
	}

	@Override
	public String logOutAdmin(String key) throws LogException {

		CurrentAdminSession currentAdmin = adminSessionDao.findByUpdateKey(key);

		if (currentAdmin == null)
			throw new LogException("Without Log In can't perform Log Out.");

		adminSessionDao.delete(currentAdmin);

		return "Log Out Successful";
	}

}
