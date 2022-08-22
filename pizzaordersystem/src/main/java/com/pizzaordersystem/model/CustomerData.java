package com.pizzaordersystem.model;

import lombok.Data;

@Data
public class CustomerData {

	private int customerId;
	private String customerName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String gender;
	private String email;
	private long phoneNumber;

}
