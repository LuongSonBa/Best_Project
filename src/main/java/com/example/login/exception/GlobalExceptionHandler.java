package com.example.login.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFoundHander(NotFoundException ex) {
		ErrorResponse err = new ErrorResponse(404, ex.getMessage());
		return ResponseEntity.status(404).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handlerValid (MethodArgumentNotValidException ex) {
		var errors = ex.getBindingResult().getFieldErrors()
				.stream().map(e -> new ValidationError (e.getField(),e.getDefaultMessage()))
				.collect(Collectors.toList());
		ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}


// 