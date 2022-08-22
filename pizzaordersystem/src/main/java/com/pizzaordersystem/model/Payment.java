package com.pizzaordersystem.model;

import lombok.Data;

@Data
public class Payment {

	private int paymentId;
	private String customerName;
	private String couponCode;
	private int amount;
	private String mode;

}
