package com.pizzaordersystem.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Order {

	private int orderId;
	private String customerName;
	private Date dateOfOrder;
	private String statusType;
	private int orderItemsId;

}
