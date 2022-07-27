package com.infosys.booking.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception,WebRequest web){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(exception.getMessage(),web.getDescription(false),new Date()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WeCareException.class)
	public ResponseEntity<ErrorResponse> handleWecareException(WeCareException exception,WebRequest web){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(exception.getMessage(),web.getDescription(false),new Date()),exception.getStatus());
		
	}
	
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String,String> errors=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String FieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(FieldName, message);
		}
		);
		
		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
		
	}

}
