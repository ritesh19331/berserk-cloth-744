package com.fastrack.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.BusException;
import com.fastrack.exception.ReservationException;
import com.fastrack.exception.UserException;
import com.fastrack.model.Bus;
import com.fastrack.model.CurrentAdminSession;
import com.fastrack.model.CurrentUserSession;
import com.fastrack.model.Reservation;
import com.fastrack.model.User;
import com.fastrack.repository.AdminSessionDao;
import com.fastrack.repository.BusDao;
import com.fastrack.repository.ReservationDao;
import com.fastrack.repository.UserDao;
import com.fastrack.repository.UserSessionDao;


@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationDao rDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Autowired
	private BusDao busDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	

	@Override
	public Reservation addReservation(Reservation reservation,Integer busId, String key) throws ReservationException, UserException, BusException {
		
		CurrentUserSession currentUserSession =  userSessionDao.findByUpdateKey(key);
		
		if(currentUserSession==null) {
			throw new UserException("User not logged in");
		}
		
		Optional<User> opt2=userDao.findById(currentUserSession.getUserId());
		User user=opt2.get();
		reservation.setRUser(user);
		
		Optional<Bus> opt=busDao.findById(busId);
		if(opt.isEmpty()) throw new BusException("Bus not found with id :"+busId);
		
		
		
		Bus bus=opt.get();
		Integer seats=bus.getAvailableSeats();
		if(seats<=0) throw new ReservationException("Seats not available try again");
		
		bus.setAvailableSeats(bus.getAvailableSeats()-1);
		
		reservation.setRBus(bus);
		
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