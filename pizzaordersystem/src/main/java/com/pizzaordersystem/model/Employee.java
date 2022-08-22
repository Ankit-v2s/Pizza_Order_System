package com.pizzaordersystem.model;

import lombok.Data;

@Data
public class Employee {

	private int employeeId;
	private String employeeName;
	private String email;
	private String gender;
	private String addressLine1;
	private String addressLine2;
	private String cityName;
	private String stateName;
	private String countryName;
	private long phoneNumber;

}
