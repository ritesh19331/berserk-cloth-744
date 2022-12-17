package com.fastrack.service;

import java.util.List;

import com.fastrack.exception.BusException;
import com.fastrack.exception.FeedbackException;
import com.fastrack.exception.ReservationException;
import com.fastrack.exception.UserException;
import com.fastrack.model.Feedback;

public interface FeedbackService {
	//user and bus and check if he/she has reservation or not 
	public Feedback addFeedback(Feedback feedback,Integer busId,Integer reservationId, String key) throws FeedbackException, UserException, BusException,ReservationException;
	public Feedback updateFeedback(Feedback feedback, String key) throws FeedbackException, UserException;
	public Feedback viewFeedback(Integer feedbackId, String key) throws FeedbackException, UserException;
	public List<Feedback> viewAllFeedback(String key) throws FeedbackException,UserException;
}
