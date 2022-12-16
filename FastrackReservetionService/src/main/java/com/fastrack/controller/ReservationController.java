package com.fastrack.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.ReservationException;
import com.fastrack.exception.UserException;
import com.fastrack.model.Reservation;
import com.fastrack.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/reservation/{key}")
	public ResponseEntity<Reservation> makeReservation(@RequestBody Reservation reservation, @PathVariable("key")String key) throws ReservationException, UserException{
		
		Reservation	reservation1 = reservationService.addReservation(reservation,key);
		
		return new ResponseEntity<Reservation>(reservation1, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updatereservation/{key}")
	public ResponseEntity<Reservation> editReservation(@RequestBody Reservation reservation, @PathVariable("key") String key) throws ReservationException, UserException{
		
		Reservation reservation2 =  reservationService.updateReservation(reservation, key);
		
		return new ResponseEntity<Reservation>(reservation2, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletereservation/{key}")
	
	public ResponseEntity<Reservation> cancelReservation(@PathVariable("resId") Integer reservationId, @PathVariable("key") String key) throws ReservationException, UserException{
		
		Reservation reservation = reservationService.deleteReservation(reservationId, key);
		
		return new ResponseEntity<Reservation>(reservation,HttpStatus.OK);
	}
	
	@GetMapping("/showreservation/{resId}/{key}")
	public ResponseEntity<Reservation> showReservation(@PathVariable("resId") Integer reservationId, @PathVariable("key") String key) throws ReservationException, UserException{
		
		Reservation reservation = reservationService.viewReservation(reservationId, key);
		
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}
	
	@GetMapping("/showallreservation/{key}")
	public ResponseEntity<List<Reservation>> showAllReservation(@PathVariable("key") String key) throws ReservationException, AdminException{
		
		List<Reservation> reservations =  reservationService.viewAllReservation(key);
		
		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
		
	}
	
	@GetMapping("/showallreservationsbydate/{date}/{key}")
	public ResponseEntity<List<Reservation>> showAllReservationsByDate(@PathVariable("date")LocalDate date, @PathVariable("key")String key) throws ReservationException, AdminException{
		
		List<Reservation> reservations = reservationService.getAllReservationsByDate(date, key);
		
		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
	}

}
