package com.frs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
	@NotNull(message = "Username Should Not be blank")
	@Size(min = 4,max = 15,message = "Provide User Name In Between 6 To 15 Char")
	private String userName;

	@NotNull(message="Password Should Not be blank")
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be have letter A-Z,a-z and charecter line @,%,&,_ Only Allow")
	private String adminPassword;
	
	@NotNull(message="Email Should Not be blank")
	@Email(message="Please Provide Email with correct format")
	private String email;

	

	
}