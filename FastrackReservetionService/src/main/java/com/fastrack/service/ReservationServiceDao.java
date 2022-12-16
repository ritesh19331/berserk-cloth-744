package com.fastrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.repository.ReservationDao;



public class ReservationServiceDao implements ReservationService{
	
	@Autowired
	private ReservationDao rDao;
	
	

}
