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
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> adminException(AdminException exp, WebRequest webRequest){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(LocalDateTime.now(),exp.getMessage(),webRequest.getDescription(false)),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LogException.class)
	public ResponseEntity<MyErrorDetails> logException(LogException exp, WebRequest webRequest){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(LocalDateTime.now(),exp.getMessage(),webRequest.getDescription(false)),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userException(UserException exp, WebRequest webRequest){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(LocalDateTime.now(),exp.getMessage(),webRequest.getDescription(false)),HttpStatus.BAD_REQUEST);
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
