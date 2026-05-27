package com.test_junit_1.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.test_junit_1.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(
			ResourceNotFoundException exception, WebRequest request){
		
		ErrorDto error = new ErrorDto(exception.getMessage(), new Date(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(
			Exception exception, WebRequest request){
		
		ErrorDto error = new ErrorDto(exception.getMessage(), new Date(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
