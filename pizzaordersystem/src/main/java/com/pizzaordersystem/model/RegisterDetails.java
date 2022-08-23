package com.pizzaordersystem.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RegisterDetails {

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z ]*$",message = "Only Alphabets allowed")
	private String name;
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
	@NotBlank
	@Pattern(regexp = "^[a-z]+[a-z0-9.+]+@[A-Za-z]+[.]{1}[A-Za-z]{2,}$",message = "Email should be in proper format")
	private String email;
	private String gender;
	@NotNull
	@Min(value = 10,message = "Phone number should be of minimum 10 digits")
	private long phoneNumber;
	@NotBlank
	private String userName;
	@NotBlank
	private String password;

}
