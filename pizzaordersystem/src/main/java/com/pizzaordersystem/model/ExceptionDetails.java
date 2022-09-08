package com.pizzaordersystem.model;

import lombok.Data;

@Data
public class ExceptionDetails {

	private String message;

	public ExceptionDetails(String message) {
		super();
		this.setMessage(message);
	}
	
}
