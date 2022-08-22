package com.pizzaordersystem.model;

import lombok.Data;

@Data
public class Feedback {

	private int feedbackId;
	private String customerName;
	private String feedbackStatusType;
	private String comments;

}
