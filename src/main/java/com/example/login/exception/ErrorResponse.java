package com.example.login.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
	private int status;
	private String message;
	private LocalDateTime timestamp;
	private List<ValidationError> errors;
	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ErrorResponse(int status, String message, List<ValidationError> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}
	

}
