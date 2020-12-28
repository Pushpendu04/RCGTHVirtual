package com.cognizant.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ProfileNotFoundException.class)
	public final ResponseEntity<Object> handleProfileNotFoundException(ProfileNotFoundException ex, WebRequest request)
			throws Exception {
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ProfileAlreadyExistException.class)
	public final ResponseEntity<Object> handleProfileAlreadyExistException(ProfileAlreadyExistException ex, WebRequest request)
			throws Exception {
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);

	}
}
