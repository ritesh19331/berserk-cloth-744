package com.frs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userLoginId;
	@NotNull(message = "Must to Provide value")
	@Size(min = 6,max = 15,message = "Provide User Name In Between 6 To 15 Char")
	private String userName;
	@NotNull(message = "Must to Provide value")
	@Size(min = 8,max = 15,message = "Provide Password In Between 8 To 15 Char")
	private String password;
	@NotNull(message = "Must to Provide value")
	@Size(min = 2,max = 12,message = "Provide First Name In Between 2 To 12 Char")
	private String firstName;
	@NotNull(message = "Must to Provide value")
	@Size(min = 2,max = 12,message = "Provide Last Name In Between 2 To 12 Char")
	private String lastName;
	@NotNull(message = "Must to Provide value")
	@Size(min = 10,message = "Provide Last Name In Between 2 To 12 Char")
	
	private Long contact;
	
	@Email
	private String email;
	
	private Reservation reservation;
}
