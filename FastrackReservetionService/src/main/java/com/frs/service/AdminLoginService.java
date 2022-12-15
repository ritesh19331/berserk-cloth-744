package com.frs.service;

import com.frs.exception.LogException;
import com.frs.model.AdminLoginDTO;
import com.frs.model.CurrentAdminSession;

public interface AdminLoginService {
	
	public CurrentAdminSession logInAdmin(AdminLoginDTO dto)throws LogException;

	public String logOutAdmin(String key)throws LogException;
}
