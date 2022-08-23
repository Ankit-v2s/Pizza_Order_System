package com.pizzaordersystem.exception;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pizzaordersystem.model.ExceptionDetails;

@ControllerAdvice
public class ExceptionController {

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
		ExceptionDetails details = new ExceptionDetails(ex.getMessage());
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public void nullPointerException(NullPointerException ex) {
		System.err.println(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> getMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String filedName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(filedName, message);
		}
		return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InvalidFieldException.class)
	public ResponseEntity<?> getInvalidFieldException(InvalidFieldException ex) {
		Map<String, String> map = new HashMap<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String filedName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(filedName, message);
		}
		return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
	}
}
