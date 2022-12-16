package com.fastrack.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationId;
	
	@NotNull(message = "Provide Reservation Status")
	private String reservationStatus;
	
	@NotNull(message = "Provide Reservation Type")
	private String reservationType;
	
	@NotNull(message = "Provide Reservation Date")
	private LocalDate reservationDate;
	
	@NotNull(message = "Provide Reservation Time")
	private LocalTime reservationTime;
	
	@NotNull(message = "Provide From where to book the seat by user")
	private String source;
	
	@NotNull(message = "Provide User Destination")
	private String destination;
	
//	@ManyToOne
//	private Bus bus;
	
	@ManyToOne
	private User user;
	

}