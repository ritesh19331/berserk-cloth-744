package com.fastrack.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

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
	private String reservationStatus;
	private String reservationType;
	private LocalDate reservationDate;
	
	private LocalTime reservationTime;
	private String source;
	private String destination;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Bus rBus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User rUser;
	

}
