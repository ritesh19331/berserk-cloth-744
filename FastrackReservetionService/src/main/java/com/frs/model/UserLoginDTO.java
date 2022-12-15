package com.frs.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserLoginDTO {
	
	private String mobileNumber;
	
	private String email;
	
	@NotNull(message="password should not be null")
	private String password;
	
//	private String type;
}
