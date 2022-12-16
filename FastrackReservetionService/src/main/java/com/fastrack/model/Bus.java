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
	@NotNull(message = "Provide Bus Name, Because It Must Needed")
	private String busName;
	@NotNull(message = "Provide Bus Driver Name, Because It Must Needed")
	private String driverName;
	@NotNull(message = "Provide Bus Type Like Ac, Non-Ac, Sleeper, Double Decar, Single Decar and etc.")
	private String busType;
	
	@NotNull(message = "Provide Bus Arrival Time")
	private LocalTime arrivalTime;
	@NotNull(message = "Provide Bus Departure Time")
	private LocalTime departureTime;
	@NotNull(message = "Provide Total Number Of Seats")
	private Integer seats;
	@NotNull(message = "Provide Avaiable Number Of Seats")
	private Integer avaiableSeats;

	@NotNull(message = "Provide Amount Of Per Seat")
	private Integer pricePerSeat;
	
	@ManyToOne
	@JoinColumn(name="B_Route")
	private Route busRoute;
	
	@OneToMany(mappedBy = "fBus",cascade = CascadeType.ALL)
	private List<Feedback> bFeedback=new ArrayList<>();
	
	@OneToMany(mappedBy = "rBus",cascade =  CascadeType.ALL)
	private List<Reservation> reservations=new ArrayList<>();
}
