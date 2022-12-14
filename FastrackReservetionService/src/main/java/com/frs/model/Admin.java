package com.frs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer adminId;
	
	@NotNull(message = "Name Should Not be blank")
	@Size(min = 6,max = 15,message = "Provide User Name In Between 6 To 15 Char")
	private String adminUsername;
	
	@NotNull(message = "Password Should Not be blank")
	@Size(min = 8,max = 15,message = "Provide Password In Between 8 To 15 Char")
	@Pattern(regexp = "A-Za-z0-9@%&_",message = "Password must be have letter A-Z,a-z and charecter line @,%,&,_ Only Allow")
	private String adminPassword;
	
	
	

}
