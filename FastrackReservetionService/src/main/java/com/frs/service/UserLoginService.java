package com.frs.service;

import com.frs.exception.LogException;
import com.frs.model.CurrentUserSession;
import com.frs.model.UserLoginDTO;

public interface UserLoginService {
	
	public CurrentUserSession logInUser(UserLoginDTO dto)throws LogException;

	public String logOutUser(String key)throws LogException;
}
