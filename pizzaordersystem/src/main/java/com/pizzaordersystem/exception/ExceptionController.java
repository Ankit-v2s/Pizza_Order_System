package com.pizzaordersystem.exception;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaordersystem.model.ExceptionDetails;

@ControllerAdvice
public class ExceptionController {

	private static final String SOMETHING_WENT_WRONG = "Something went wrong : ";
	private static final String ERROR = "Error";

	@ExceptionHandler(CredentialCheckerException.class)
	public ResponseEntity<?> credentialCheckerException(CredentialCheckerException ex) {
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
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String filedName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(filedName, message);
		}
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(SQLException.class)
	public String sqlException(SQLException ex, ModelAndView modelAndView) {
		System.out.println(SOMETHING_WENT_WRONG + ex.getMessage());
		return ERROR;
	}

	@ExceptionHandler(ClassNotFoundException.class)
	public String classNotFoundException(ClassNotFoundException ex, ModelAndView modelAndView) {
		System.out.println(SOMETHING_WENT_WRONG + ex.getMessage());
		return ERROR;
	}

	@ExceptionHandler(Exception.class)
	public String exception(Exception ex) {
		System.out.println(SOMETHING_WENT_WRONG + ex.getMessage());
		return ERROR;
	}
}
