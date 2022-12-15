package com.fastrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastrack.model.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Integer>{

}
