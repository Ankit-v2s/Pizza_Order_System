package com.pizzaordersystem.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Employee {

	private int employeeId;
	private String employeeName;
	@NotBlank
	@Pattern(regexp = "^[a-z]+[a-z0-9.+]+@[A-Za-z]+[.]{1}[A-Za-z]{2,}$",message = "Email should be in proper format")
	private String email;
	private String gender;
	@NotBlank
	private String addressLine1;
	@NotBlank
	private String addressLine2;
	@NotBlank
	private String cityName;
	@NotBlank
	private String stateName;
	@NotBlank
	private String countryName;
	@NotBlank
	@Pattern(regexp = "^(0|[1-9][0-9]*)$",message = "Only numbers allowed")
	@Size(min = 10,message = "Phone number should be of minimum 10 digits")
	private String phoneNumber;

}
