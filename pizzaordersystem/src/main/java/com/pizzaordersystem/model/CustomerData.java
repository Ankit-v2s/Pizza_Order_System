package com.pizzaordersystem.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerData {

	private int customerId;
	private String customerName;
	@NotBlank
	private String address1;
	@NotBlank
	private String address2;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
	@NotBlank
	private String country;
	private String gender;
	@NotBlank
	@Pattern(regexp = "^[a-z]+[a-z0-9.+]+@[A-Za-z]+[.]{1}[A-Za-z]{2,}$",message = "Email should be in proper format")
	private String email;
	@NotBlank
	@Pattern(regexp = "^(0|[1-9][0-9]*)$",message = "Only numbers allowed")
	@Size(min = 10,message = "Phone number should be of minimum 10 digits")
	private String phoneNumber;

}
