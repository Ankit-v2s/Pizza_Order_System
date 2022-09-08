package com.pizzaordersystem.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Feedback {

	private int feedbackId;
	private String customerName;
	@NotBlank
	private String feedbackStatusType;
	@NotBlank
	private String comments;

}
