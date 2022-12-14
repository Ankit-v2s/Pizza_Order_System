package com.pizzaordersystem.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidCredentialException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.exception.ZeroAmountException;
import com.pizzaordersystem.model.ExceptionDetails;

/**
 * @author Ankit Madhavi
 */
@ControllerAdvice
public class ExceptionHandlerController {

	private static final String SOMETHING_WENT_WRONG = "Something went wrong : ";
	private static final String ERROR = "Error";
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);


	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<?> credentialCheckerException(InvalidCredentialException ex) {
		ExceptionDetails details = new ExceptionDetails(ex.getMessage());
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ZeroAmountException.class)
	public ResponseEntity<?> zeroAmountException(ZeroAmountException ex) {
		ExceptionDetails details = new ExceptionDetails(ex.getMessage());
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidFieldException.class)
	public ResponseEntity<?> getInvalidFieldException(InvalidFieldException ex) {
		Map<String, String> map = new HashMap<>();
//		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
//			String filedName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			map.put(filedName, message);
//		}
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String filedName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(filedName, message);
		});
		
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CredentialsNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void credentialsNotValidException(CredentialsNotValidException ex) {
		LOGGER.info(SOMETHING_WENT_WRONG + ex.getMessage());
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void sqlException(SQLException ex) {
		LOGGER.info(SOMETHING_WENT_WRONG + ex.getMessage());
	}

	@ExceptionHandler(ClassNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void classNotFoundException(ClassNotFoundException ex) {
		LOGGER.info(SOMETHING_WENT_WRONG + ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public String exception(Exception ex) {
		LOGGER.info(SOMETHING_WENT_WRONG + ex.getMessage());
		return ERROR;
	}
}
