package com.fastrack.service;

import com.fastrack.exception.LogException;
import com.fastrack.model.AdminLoginDTO;
import com.fastrack.model.CurrentAdminSession;

public interface AdminLoginService {
	
	public CurrentAdminSession logInAdmin(AdminLoginDTO dto)throws LogException;

	public String logOutAdmin(String key)throws LogException;
}
