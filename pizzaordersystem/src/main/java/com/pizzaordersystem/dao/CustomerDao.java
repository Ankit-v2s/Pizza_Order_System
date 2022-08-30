package com.pizzaordersystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;

public interface CustomerDao {

	CustomerData getCustomer(LoginCredentials loginCredentials) throws SQLException;

	List<FeedbackStatus> getFeedbackStatus(List<FeedbackStatus> feedbackStatusList) throws SQLException;

	void updateCustomer(CustomerData customerData, int customerId) throws SQLException;

	void addFeedback(Feedback feedback, LoginCredentials loginCredentials) throws SQLException;

	int calculateAmount() throws SQLException;

	void addOrder(LoginCredentials loginCredentials) throws SQLException;

	void addItem(List<PizzaOrder> cart) throws SQLException;

	void addPayment(LoginCredentials credentials, Payment payment) throws SQLException;

	int discountPrice(PizzaOrder pizzaOrder) throws SQLException;

}