package com.pizzaordersystem.model;

public class Feedback {

	private int feedbackId;
	private String customerName;
	private String feedbackStatusType;
	private String comments;

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFeedbackStatusType() {
		return feedbackStatusType;
	}

	public void setFeedbackStatusType(String feedbackStatusType) {
		this.feedbackStatusType = feedbackStatusType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
