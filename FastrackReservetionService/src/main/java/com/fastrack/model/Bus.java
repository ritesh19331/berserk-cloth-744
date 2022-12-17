package com.fastrack.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	
	@NotNull(message = "Bus Name Mandatory")
	private String busName;
	@NotNull(message = "Driver Name Mandatory")
	private String driverName;
	@NotNull(message = "Bus Type Mandatory")
	private String busType;
	
	@NotNull(message = "Arrival Time Mandatory")
	private LocalTime arrivalTime;
	@NotNull(message = "Departure Time Mandartory")
	private LocalTime departureTime;
	@NotNull(message = "Number Of Seats Mandatory")
	private Integer seats;
	@NotNull(message = "AvailableSeats Mandatory")
	private Integer availableSeats;

	@NotNull(message = "Price Per Seats mandatory")
	private Integer pricePerSeat;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="B_Route")
	private Route busRoute;
	
	@OneToMany(mappedBy = "fBus",cascade = CascadeType.ALL)
	private List<Feedback> bFeedback=new ArrayList<>();
	
	@OneToMany(mappedBy = "rBus",cascade =  CascadeType.ALL)
	private List<Reservation> reservations=new ArrayList<>();
}
