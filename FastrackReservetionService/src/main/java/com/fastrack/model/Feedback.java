package com.fastrack.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedBackId;
	
	@Min(value=1, message="Minimum value is 1")
	@Max(value=5, message="Maximum value is 5")
	@NotNull(message="Driver Rating mandatory")
	private Integer driverRating;
	
	@Min(value=1, message="Minimum value is 1")
	@Max(value=5, message="Maximum value is 5")
	
	@NotNull(message="Service Rating mandatory")
	@Max(value=5, message="Maximum value is 5")
	private Integer serviceRating;
	
	@Min(value=1, message="Minimum value is 1")
	@Max(value=5, message="Maximum value is 5")
	@NotNull(message="Overall Rating mandatory")
	private Integer overallRating;
	
	private String comments;
	
	private LocalDate feedbackdate;

//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.ALL)
//	private User fUser;
	
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private User fUser;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Bus fBus;
	

}
