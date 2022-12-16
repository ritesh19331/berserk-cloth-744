package com.fastrack.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.ReservationException;
import com.fastrack.exception.UserException;
import com.fastrack.model.CurrentAdminSession;
import com.fastrack.model.CurrentUserSession;
import com.fastrack.model.Reservation;
import com.fastrack.repository.AdminSessionDao;
import com.fastrack.repository.ReservationDao;
import com.fastrack.repository.UserSessionDao;


@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationDao rDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	

	@Override
	public Reservation addReservation(Reservation reservation, String key) throws ReservationException, UserException {
		
		CurrentUserSession currentUserSession =  userSessionDao.findByUpdateKey(key);
		
		if(currentUserSession==null) {
			throw new UserException("User not logged in");
		}
		
		if(reservation.getRBus().getAvaiableSeats()==0) {
			throw new ReservationException("No seats available");
		}
		
		return  rDao.save(reservation);
		
	}

	@Override
	public Reservation updateReservation(Reservation reservation, String key) throws ReservationException, UserException {
		
		CurrentUserSession currentUserSession =  userSessionDao.findByUpdateKey(key);
		
		if(currentUserSession==null) {
			throw new UserException("User not logged in");
		}
		
		Optional<Reservation> opt = rDao.findById(reservation.getReservationId());
		
		if(opt.isEmpty()) {
			
			throw new ReservationException("Reservation not found"); 
		}
		
		return rDao.save(reservation);
	}

	@Override
	public Reservation deleteReservation(Integer reservationId, String key) throws ReservationException, UserException{
		
		CurrentUserSession currentUserSession =  userSessionDao.findByUpdateKey(key);
		
		if(currentUserSession==null) {
			throw new UserException("User not logged in");
		}
		
		Optional<Reservation> opt = rDao.findById(reservationId);
		
		if(opt.isEmpty()) {
			
			throw new ReservationException("Reservation not found"); 
		}
		
		Reservation reservation = opt.get();
		
		rDao.delete(reservation);
		
		return reservation;
	}

	@Override
	public Reservation viewReservation(Integer reservationId, String key) throws ReservationException, UserException{
		
		CurrentUserSession currentUserSession =  userSessionDao.findByUpdateKey(key);
		
		if(currentUserSession==null) {
			throw new UserException("User not logged in");
		}
		
		Optional<Reservation> opt = rDao.findById(reservationId);
		
		if(opt.isEmpty()) {
			
			throw new ReservationException("Reservation not found"); 
		}
		return opt.get();
	}

	@Override
	public List<Reservation> viewAllReservation(String key) throws ReservationException, AdminException {
		
		CurrentAdminSession currentAdminSession = adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null) {
			throw new AdminException("Admin is not logged in");
		}
		
		List<Reservation> reservations =  rDao.findAll();
		
		if(reservations.isEmpty()) {
			throw new ReservationException("No reservations found");
		}
		
		return reservations;
	
	}

	@Override
	public List<Reservation> getAllReservationsByDate(LocalDate date, String key)throws AdminException, ReservationException{
		
		CurrentAdminSession currentAdminSession = adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null) {
			throw new AdminException("Admin is not logged in");
		}
		
		List<Reservation> reservations = rDao.findByReservationDate(date);
		
		if(reservations.isEmpty()) {
			throw new ReservationException("No reservation found on: "+date);
		}
		
		return reservations;
	}

	
	
	
	
	

}