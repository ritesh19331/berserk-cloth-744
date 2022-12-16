package com.fastrack.service;

import java.util.List;

import com.fastrack.exception.FeedbackException;
import com.fastrack.exception.UserException;
import com.fastrack.model.Feedback;

public interface FeedbackService {
	public Feedback addFeedback(Feedback feedback, String key) throws FeedbackException, UserException;
	public Feedback updateFeedback(Feedback feedback, String key) throws FeedbackException, UserException;
	public Feedback viewFeedback(Integer feedbackId, String key) throws FeedbackException, UserException;
	public List<Feedback> viewAllFeedback(String key) throws FeedbackException,UserException;
}
