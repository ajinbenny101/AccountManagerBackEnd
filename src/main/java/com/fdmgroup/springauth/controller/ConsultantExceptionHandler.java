package com.fdmgroup.springauth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fdmgroup.springauth.exceptions.ConsultantExistsException;
import com.fdmgroup.springauth.exceptions.ConsultantNotFoundException;



@ControllerAdvice
public class ConsultantExceptionHandler {

	@ExceptionHandler(value = ConsultantNotFoundException.class)
	public ResponseEntity<String> handleBookNotFoundException(ConsultantNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(value = ConsultantExistsException.class)
	public ResponseEntity<String> handleEmployeeExistsException(ConsultantExistsException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<ObjectError> objectErrors = e.getAllErrors();

		StringBuilder errors = new StringBuilder();

		objectErrors.forEach(error -> errors.append(error.getDefaultMessage() + ", "));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
	}
	
}




	

	

