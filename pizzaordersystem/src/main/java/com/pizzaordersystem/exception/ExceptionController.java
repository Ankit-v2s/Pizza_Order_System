package com.pizzaordersystem.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println(ex.getMessage());
	}

	@ExceptionHandler(ClassNotFoundException.class)
	public void classNotFoundException(ClassNotFoundException ex) {
		System.out.println(ex.getMessage());
	}
	
	@ExceptionHandler(CredentialCheckerException.class)
	public ResponseEntity<?> credentialCheckerExceptionException(CredentialCheckerException ex) {
		CredentialCheckerException checkerException=new CredentialCheckerException("Invalid Credentials");
		return new ResponseEntity<>(checkerException,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public void nullPointerException(NullPointerException ex) {
		System.err.println(ex.getMessage());
	}
}
