package com.fastrack.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<MyErrorDetails> reservationExcptionHandler(ReservationException re, WebRequest webRequest){
		
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(LocalDateTime.now(),re.getMessage(),webRequest.getDescription(false)),HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> validatedException(MethodArgumentNotValidException exp,WebRequest webRequest){
		
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(LocalDateTime.now(),exp.getMessage(),webRequest.getDescription(false)),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandler(NoHandlerFoundException exp,WebRequest webRequest){
		
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(LocalDateTime.now(),exp.getMessage(),webRequest.getDescription(false)),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exception(Exception exp,WebRequest webRequest){ 
		
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(LocalDateTime.now(),exp.getMessage(),webRequest.getDescription(false)),HttpStatus.BAD_REQUEST);
	}

}
