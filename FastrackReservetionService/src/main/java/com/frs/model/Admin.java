package com.frs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotNull
	@Size(min = 6,max = 15,message = "Provide User Name In Between 6 To 15 Char")
	private String adminUsername;
	
	@NotNull
	@Size(min = 8,max = 15,message = "Provide Password In Between 8 To 15 Char")
	private String adminPassword;
	

}
