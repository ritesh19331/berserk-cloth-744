

package com.fastrack.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserLoginDTO {
	
//	private String mobileNumber;
	
	@NotNull(message = "Email should not be null")
	private String email;
	
	@NotNull(message="password should not be null")
	private String userPassword;
	
//	private String type;
}
