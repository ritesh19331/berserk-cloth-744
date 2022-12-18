package com.fastrack.service;


import java.time.LocalDate;
import java.util.List;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.BusException;
import com.fastrack.exception.ReservationException;
import com.fastrack.exception.UserException;
import com.fastrack.model.Reservation;



public interface ReservationService {
	// reservation only possible if there exist a bus and number of seats
	public Reservation addReservation(Reservation reservation,Integer busId, String key )throws ReservationException, UserException, BusException;
	
	public Reservation updateReservation(Reservation reservation, String key)throws ReservationException, UserException;
	
	public Reservation deleteReservation(Integer reservationId, String key)throws ReservationException, UserException;
	
	public Reservation viewReservation(Integer reservationId, String key)throws ReservationException, UserException;
	
	public List<Reservation> viewAllReservation(String key)throws ReservationException, AdminException;
	
	public List<Reservation> getAllReservationsByDate(LocalDate date, String key)throws ReservationException, AdminException;


}