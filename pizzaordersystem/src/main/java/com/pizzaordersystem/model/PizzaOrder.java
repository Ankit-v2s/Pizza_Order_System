package com.pizzaordersystem.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PizzaOrder {

	@NotBlank
	private String pizzaName;
	@Min(value = 1,message = "Quantity should be selected")
	private int quantity;
	private int amount;
	private String couponCode;

}
