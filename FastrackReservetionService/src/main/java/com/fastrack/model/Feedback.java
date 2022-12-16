package com.fastrack.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

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
	@Size(min = 1, max = 5 ,message = "Rating Driver in between of 1-5 Points")
	private Integer driverRatting;
	@Size(min = 1, max = 5 ,message = "Rating Service in between of 1-5 Points")
	private Integer serviceRating;
	@Size(min = 1, max = 5 ,message = "Rating Overall Service in between of 1-5 Points")
	private Integer overallRating;
	private String comments;
	private LocalDate feedbackdate;

	@ManyToOne(cascade = CascadeType.ALL)
	private User fUser;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Bus fBus;
	

}
