package com.pizzaordersystem.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class InvalidFieldException extends BindException{

	private static final long serialVersionUID = 1L;

	public InvalidFieldException(BindingResult result) {
		super(result);
	}
}
