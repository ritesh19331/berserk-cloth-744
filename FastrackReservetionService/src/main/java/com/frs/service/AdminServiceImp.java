package com.frs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frs.exception.AdminException;
import com.frs.model.Admin;
import com.frs.model.CurrentAdminSession;
import com.frs.repository.AdminDao;
import com.frs.repository.AdminSessionDao;

@Service
public class AdminServiceImp implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Override
	public Admin createAdmin(Admin admin) throws AdminException {
		
		Admin existingAdmin= adminDao.findByEmail(admin.getEmail());
		
		if(existingAdmin != null) 
			throw new AdminException("Admin already registered with this Email");
		
		return adminDao.save(admin);
	
	}
	
	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException {
		
		CurrentAdminSession loggedInAdmin= adminSessionDao.findByUpdateKey(key);
		
		if(loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key to update Admin Details");
		}
		
		if(admin.getAdminId() == loggedInAdmin.getAdminId()) {
			
			return adminDao.save(admin);
		}
		else
			throw new AdminException("Please login with admin detailes");
	}
}