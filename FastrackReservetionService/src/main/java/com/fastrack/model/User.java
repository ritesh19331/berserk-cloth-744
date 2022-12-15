package com.fastrack.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
	
	@NotNull(message = "User Name Must Need. Please Provide Right Value")
	@Size(min = 6,max = 15,message = "Provide User Name In Between 6 To 15 Char")
	private String userName;
	
	@NotNull(message = "Password Must Need. Please Provide Right Value")
	@Size(min = 8,max = 15,message = "Provide Password In Between 8 To 15 Char")
	@Pattern(regexp = "A-Za-z0-9@%&_",message = "Password must be have letter A-Z,a-z and charecter line @,%,&,_ Only Allow")
	private String password;
	
	@NotNull(message = "First Name Must Need. Please Provide Right Value")
	@Size(min = 2,max = 12,message = "Provide User Name In Between 2 To 12 Char")
	private String firstName;
	
	@NotNull(message = "Last Name Must Need. Please Provide Right Value")
	@Size(min = 2,max = 12,message = "Provide User Name In Between 2 To 12 Char")
	private String lastName;
	
	@NotNull(message = "Contact Number Must Need For An User. Please Provide Right Value")
	@Pattern(regexp = "[6789]{10}", message = "Enter Provide 10 digit Mobile Number Which Strat With 6,7,8,9")
	private Long contact;
	
	@Email
	@NotNull(message = "Email Id Must Need With Poper Email Stucture. Please Provide Right Detailes")
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "rUser")
	private List<Reservation> userReservations = new ArrayList<>();
	
	@OneToMany(mappedBy ="fUser",cascade = CascadeType.ALL )
	private List<Feedback> uFeedbacks=new ArrayList<>();
	
}
