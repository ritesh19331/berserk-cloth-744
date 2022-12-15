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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@NotNull(message = "First Name Must Need. Please Provide Right Value")
	@Size(min = 2,max = 12,message = "Provide First Name In Between 2 To 12 Char")
	private String firstName;
	
	@NotNull(message = "Last Name Must Need. Please Provide Right Value")
	@Size(min = 2,max = 12,message = "Provide Last Name In Between 2 To 12 Char")
	private String lastName;
	
	@NotNull(message = "Password Must Need. Please Provide Right Value")
	@Size(min = 8,max = 15,message = "Provide Password In Between 8 To 15 Char")
	@Pattern(regexp = "A-Za-z0-9@%&_",message = "Password must be have letter A-Z,a-z and charecter line @,%,&,_ Only Allow")
	private String password;
	
	
	@NotNull(message = "Contact Number Must Need For An User. Please Provide Right Value")
	@Pattern(regexp = "[6789]{10}", message = "Enter Provide 10 digit Mobile Number Which Strat With 6,7,8,9")
	private String mobileNumber;
	
	@Email
	@NotNull(message = "Email Id Must Need With Poper Email Stucture. Please Provide Right Detailes")
	private String email;
	
//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
//	private List<Reservation> reservations =new ArrayList<>();
	


	
}