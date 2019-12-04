package com.credit.experian.CustomExceptions;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
	

	private String message;
	private String details;
	private int errorcode;
	
	public ErrorMessage() {
		super();
	}
	
	public ErrorMessage(String message, String details,int errorcode) {
	super();
	this.message=message;
	this.details=details;
	this.errorcode=errorcode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}


}
