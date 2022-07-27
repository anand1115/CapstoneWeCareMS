package com.infosys.coach.exception;


import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class WeCareException extends RuntimeException{

	private HttpStatus status;
	private String message;
	public WeCareException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	
}
