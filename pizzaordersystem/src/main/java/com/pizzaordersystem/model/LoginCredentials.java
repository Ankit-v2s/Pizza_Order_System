package com.pizzaordersystem.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginCredentials {

	private int loginId;	
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
	private int employeeId;
	private int customerId;

}
