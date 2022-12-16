package com.fastrack.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotNull(message = "Username Should Not be blank")
	@Size(min = 4,max = 15,message = "Provide User Name In Between 6 To 15 Char")
	private String firstName;
	
	@NotNull(message = "Username Should Not be blank")
	@Size(min = 4,max = 15,message = "Provide User Name In Between 6 To 15 Char")
	private String lastName;

	@NotNull(message="Password Must Need. Please Provide Right Value")
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be have letter A-Z,a-z and charecter line @,%,&,_ Only Allow")
	private String userPassword;
	
	@NotNull(message="Email Should Not be blank")
	@Email(message="Please Provide Email with correct format")
	private String email;

	@NotNull(message="Contact Number Must Need For An User. Please Provide Right Value")
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Please Provide Mobile with correct format")
	@Size(min = 10)
	@Column(unique = true)
	private String mobileNumber;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Reservation> reservations = new ArrayList<>();
	
	@OneToMany(mappedBy ="fUser",cascade = CascadeType.ALL )
	private List<Feedback> uFeedbacks=new ArrayList<>();
	
}