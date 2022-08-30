package com.pizzaordersystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.exception.ZeroAmountException;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;

public interface CustomerService {

	/**
	 *@throws CredentialsNotValidException
	 *To check if only customer has access
	 */
	void checker() throws CredentialsNotValidException;
	
	/**
	 *@return CustomerData
	 *@throws SQLException
	 *To fetch the details of the particular customer who is logged in
	 */
	CustomerData fetchCustomerDetails() throws SQLException;

	/**
	 *@param customerData
	 *@param customerId
	 *@param result
	 *@throws SQLException,
	 *@throws InvalidFieldException
	 *To update the details of customer
	 */
	void updateCustomer(CustomerData customerData, int customerId, BindingResult result)
			throws SQLException, InvalidFieldException;

	/**
	 *@return List
	 *@throws SQLException,
	 *To fetch all the feedback Status available
	 */
	List<FeedbackStatus> fetchFeedbackStatus() throws SQLException;

	/**
	 *@param feedback
	 *@param result
	 *@throws SQLException,
	 *@throws InvalidFieldException
	 *To add new feedback
	 */
	void addFeedback(Feedback feedback, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 *@return int
	 *@throws SQLException,
	 *To calculate total amount of the items available in cart
	 */
	int calculate() throws SQLException;

	/**
	 *@throws SQLException,
	 *To add new order and new order items
	 */
	void addOrder() throws SQLException;

	/**
	 *@param pizza
	 *@param result
	 *@throws SQLException,
	 *@throws InvalidFieldException
	 *To add the items to the cart list
	 */
	List<PizzaOrder> addItem(PizzaOrder pizza, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 *@param pizzaOrder
	 *@return int
	 *@throws SQLException,
	 *@throws ZeroAmountException
	 *To deduct the amount according to the discount applied for the coupon
	 */
	int discountPrice(PizzaOrder pizzaOrder) throws SQLException, ZeroAmountException;

	/**
	 *@param payment
	 *@param result
	 *@throws SQLException,
	 *@throws InvalidFieldException
	 *To add new payment
	 */
	void addPayment(Payment payment, BindingResult result) throws SQLException, InvalidFieldException;

}