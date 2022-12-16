package com.fastrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.FeedbackException;
import com.fastrack.exception.UserException;
import com.fastrack.model.Feedback;
import com.fastrack.repository.FeedbackDao;
import com.fastrack.repository.UserSessionDao;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDao fDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Override
	public Feedback addFeedback(Feedback feedback, String key) throws FeedbackException, UserException {
		if(userSessionDao.findByUpdateKey(key)==null) throw new UserException("User Not Logged In");
		return fDao.save(feedback);
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
