package com.frs.service;

import com.frs.exception.AdminException;
import com.frs.model.Admin;

public interface AdminService {
	
	public Admin createAdmin(Admin admin)throws AdminException;
	
	public Admin updateAdmin(Admin admin,String key)throws AdminException;
}
