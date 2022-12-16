package com.fastrack.service;

import com.fastrack.exception.AdminException;
import com.fastrack.model.Admin;

public interface AdminService {
	
	public Admin createAdmin(Admin admin)throws AdminException;
	
	public Admin updateAdmin(Admin admin,String key)throws AdminException;
}
