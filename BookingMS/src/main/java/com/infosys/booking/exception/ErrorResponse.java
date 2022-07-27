package com.infosys.booking.exception;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
	
	private String message;
	private String path;
	private Date date;

}

