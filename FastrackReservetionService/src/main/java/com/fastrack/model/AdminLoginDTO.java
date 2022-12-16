package com.fastrack.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDTO {

	@NotNull(message = "Email should not be null")
	private String email;
	
	@NotNull(message="password should not be null")
	private String adminPassword;
	
	private String type;
}
