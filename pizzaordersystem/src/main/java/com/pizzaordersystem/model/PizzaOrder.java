package com.pizzaordersystem.model;

public class PizzaOrder {

	private String pizzaName;
	private int quantity;
	private int amount;
	private String couponCode;

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

}
