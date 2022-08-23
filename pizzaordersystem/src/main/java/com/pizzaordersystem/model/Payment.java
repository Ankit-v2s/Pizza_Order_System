package com.pizzaordersystem.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Payment {

	private int paymentId;
	private String customerName;
	private String couponCode;
	private int amount;
	@NotBlank
	private String mode;

}
