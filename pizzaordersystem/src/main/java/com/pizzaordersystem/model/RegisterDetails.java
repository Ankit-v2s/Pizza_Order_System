package com.pizzaordersystem.model;

import lombok.Data;

@Data
public class RegisterDetails {

	private String name;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String email;
	private String gender;
	private long phoneNumber;
	private String userName;
	private String password;

}
