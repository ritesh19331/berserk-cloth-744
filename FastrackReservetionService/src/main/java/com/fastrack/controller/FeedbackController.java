package com.fastrack.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fastrack.exception.BusException;
import com.fastrack.exception.FeedbackException;
import com.fastrack.exception.ReservationException;
import com.fastrack.exception.UserException;
import com.fastrack.model.Feedback;
import com.fastrack.service.FeedbackService;

@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/user/feedback/{bus_id}/{reservation_id}/{key}")
	public ResponseEntity<Feedback> addFeedbackHandler(@Valid @RequestBody Feedback feedback,@PathVariable("bus_id") Integer busId,@PathVariable("reservation_id") Integer reservationId,@PathVariable("key") String key) throws FeedbackException, UserException, BusException, ReservationException{
		Feedback addedFeedback=feedbackService.addFeedback(feedback,busId,reservationId, key);
		return new ResponseEntity<Feedback>(addedFeedback,HttpStatus.OK);
	}
	
	@PutMapping("/user/feedback/{key}")
	public ResponseEntity<Feedback> updateFeedbackHandler(@Valid @RequestBody Feedback feedback, @PathVariable("key") String key) throws FeedbackException, UserException{
		Feedback updatedFeedback=feedbackService.updateFeedback(feedback, key);
		return new ResponseEntity<Feedback>(updatedFeedback,HttpStatus.OK);
	}
	
	@GetMapping("/user/feedback/{id}/{key}")
	public ResponseEntity<Feedback> viewFeedbackByIdHandler(@Valid @PathVariable("id") Integer id,@PathVariable("key") String key) throws FeedbackException, UserException{
		Feedback viewFeedback=feedbackService.viewFeedback(id, key);
		return new ResponseEntity<Feedback>(viewFeedback,HttpStatus.OK);
	}
	
	@GetMapping("/user/feedbacks/{key}")
	public ResponseEntity<List<Feedback>> viewAllFeedbackHandler(@Valid @PathVariable("key") String key) throws FeedbackException, UserException{
		List<Feedback> feedbackList=feedbackService.viewAllFeedback(key);
		return new ResponseEntity<List<Feedback>>(feedbackList,HttpStatus.OK);
	}
}
