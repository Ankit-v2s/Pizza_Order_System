package com.pizzaordersystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;

/**
 * @author Ankit Madhavi
 *
 */
public interface CustomerDao {

	/**
	 *@param loginCredentials
	 *@return CustomerData
	 *@throws SQLException
	 *To get the specific customer's details
	 */
	CustomerData getCustomer(LoginCredentials loginCredentials) throws SQLException;
	
	/**
	 *@param feedbackStatusList
	 *@return List
	 *@throws SQLException
	 *To get all feedback status available
	 */
	List<FeedbackStatus> getFeedbackStatus(List<FeedbackStatus> feedbackStatusList) throws SQLException;

	/**
	 *@param customerData
	 *@param customerId
	 *@throws SQLException
	 *To update the data of the customer
	 */
	void updateCustomer(CustomerData customerData, int customerId) throws SQLException;

	/**
	 *@param feedback
	 *@param loginCredentials
	 *@throws SQLException
	 *To insert new feedback to the table
	 */
	void addFeedback(Feedback feedback, LoginCredentials loginCredentials) throws SQLException;

	/**
	 *@return int
	 *@throws SQLException
	 *To fetch the sum of all order items according to order
	 */
	int calculateAmount() throws SQLException;

	/**
	 *@param loginCredentials
	 *@throws SQLException
	 *To insert new order to the table
	 */
	void addOrder(LoginCredentials loginCredentials) throws SQLException;

	/**
	 *@param cart
	 *@throws SQLException
	 *To insert new items to the table
	 */
	void addItem(List<PizzaOrder> cart) throws SQLException;

	/**
	 *@param credentials
	 *@param payment
	 *@throws SQLException
	 *To insert new payment to the table
	 */
	void addPayment(LoginCredentials credentials, Payment payment) throws SQLException;

	/**
	 *@param pizzaOrder
	 *@return int
	 *@throws SQLException
	 *To get the discount according to the coupon
	 */
	int discountPrice(PizzaOrder pizzaOrder) throws SQLException;

}