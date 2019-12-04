package com.credit.experian.CustomExceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptions extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> serverErrorHandler(Exception ex){
		ErrorMessage exceptionResponse= new ErrorMessage(ex.getMessage(),"Please Contact Application Developer",500);
		
		return new ResponseEntity<ErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		 
	}
	

}
