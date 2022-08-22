package com.pizzaordersystem.model;

import lombok.Data;

@Data
public class PizzaOrder {

	private String pizzaName;
	private int quantity;
	private int amount;
	private String couponCode;

}
