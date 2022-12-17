package com.fastrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.BusException;
import com.fastrack.exception.FeedbackException;
import com.fastrack.exception.ReservationException;
import com.fastrack.exception.UserException;
import com.fastrack.model.Bus;
import com.fastrack.model.CurrentUserSession;
import com.fastrack.model.Feedback;
import com.fastrack.model.Reservation;
import com.fastrack.model.User;
import com.fastrack.repository.BusDao;
import com.fastrack.repository.FeedbackDao;
import com.fastrack.repository.ReservationDao;
import com.fastrack.repository.UserDao;
import com.fastrack.repository.UserSessionDao;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDao fDao;
	
	@Autowired
	private BusDao busDao;
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Feedback addFeedback(Feedback feedback,Integer busId,Integer reservationId, String key) throws FeedbackException, UserException, BusException, ReservationException {
		if(userSessionDao.findByUpdateKey(key)==null) throw new UserException("User Not Logged In");
		CurrentUserSession currentUserSession=userSessionDao.findByUpdateKey(key);
		
		Optional<Bus> opt=busDao.findById(busId);
		if(opt.isEmpty()) throw new BusException("Bus not found with id :"+ busId);
		Bus bus=opt.get();

		Optional<Reservation> opt1=reservationDao.findById(reservationId);
		if(opt1.isEmpty()) throw new ReservationException("Reservation not found with id :"+ reservationId);
		
		Reservation reservation=opt1.get();
		if(!bus.getReservations().contains(reservation)) throw new ReservationException("Reservation not found with id :"+reservationId+"in the bus with id :"+ busId);
		
		Optional<User> opt2=userDao.findById(currentUserSession.getUserId());
		User user=opt2.get();
		
		feedback.setFUser(user);
		user.setUFeedbacks(feedback);
		bus.getBFeedback().add(feedback);
		feedback.setFBus(bus);
		
		busDao.save(bus);

		return feedback;
	}

	@Override
	public Feedback updateFeedback(Feedback feedback, String key) throws FeedbackException, UserException {
		if(userSessionDao.findByUpdateKey(key)==null) throw new UserException("User Not Logged In");
		Optional<Feedback> opt=fDao.findById(feedback.getFeedBackId());
		if(opt==null) throw new FeedbackException("No such Feedback Exists");
		return fDao.save(feedback);
	}

	@Override
	public Feedback viewFeedback(Integer feedbackId, String key) throws FeedbackException, UserException {
		if(userSessionDao.findByUpdateKey(key)==null) throw new UserException("User Not Logged In");
		Optional<Feedback> opt=fDao.findById(feedbackId);
		if(opt.get()==null) throw new FeedbackException("No Feedback exits with id:"+ feedbackId);
		return opt.get();
	}

	@Override
	public List<Feedback> viewAllFeedback(String key) throws FeedbackException, UserException {
		if(userSessionDao.findByUpdateKey(key)==null) throw new UserException("User Not Logged In");
		List<Feedback> feedbackList=fDao.findAll();
		if(feedbackList.isEmpty()) throw new FeedbackException("Zero Feedbacks");
		return feedbackList;
	}

}
