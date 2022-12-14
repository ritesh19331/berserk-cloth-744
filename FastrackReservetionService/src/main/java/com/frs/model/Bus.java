package com.frs.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

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
	@NotNull(message = "Provide Bus Starting Point")
	private String routeFrom;
	@NotNull(message = "Provide Bus Ending Point")
	private String routeTo;
	
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
	private Route route;
}
