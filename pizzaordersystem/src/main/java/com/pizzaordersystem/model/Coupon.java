package com.pizzaordersystem.model;

import lombok.Data;

@Data
public class Coupon {

	private int couponId;
	private String couponCode;
	private int discount;

}
