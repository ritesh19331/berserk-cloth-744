package com.fastrack.service;

import com.fastrack.exception.LogException;
import com.fastrack.model.CurrentUserSession;
import com.fastrack.model.UserLoginDTO;

public interface UserLoginService {
	
	public CurrentUserSession logInUser(UserLoginDTO dto)throws LogException;

	public String logOutUser(String key)throws LogException;
}
