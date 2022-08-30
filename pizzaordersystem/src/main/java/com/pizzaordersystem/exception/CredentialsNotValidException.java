package com.pizzaordersystem.exception;

public class CredentialsNotValidException extends Exception{

	private static final long serialVersionUID = 1L;

	public CredentialsNotValidException(String message) {
		super(message);
	}

}
