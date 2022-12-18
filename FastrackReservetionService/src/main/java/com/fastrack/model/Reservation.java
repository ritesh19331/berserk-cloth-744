package com.fastrack.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Bus rBus;
	
	@JsonIgnore
	@ManyToOne
	private Bus rBus;
	
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.ALL)
//	private User rUser;
	
	@JsonIgnore
	@ManyToOne
	private User rUser;
	

}