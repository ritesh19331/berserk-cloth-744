package com.fastrack.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.ReservationException;
import com.fastrack.model.Reservation;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer>{
	

	public List<Reservation> findByReservationDate(LocalDate reservationDate) throws AdminException,ReservationException;

}