package com.pizzaordersystem.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PizzaMenu {

	private int pizzaId;
	@NotBlank
	private String pizzaName;
	@Min(value = 1, message = "Price must be entered")
	private int price;

}
