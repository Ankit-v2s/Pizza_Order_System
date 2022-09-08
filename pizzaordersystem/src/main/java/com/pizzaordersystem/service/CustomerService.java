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
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;

/**
 * @author Ankit Madhavi
 *
 */
public interface CustomerService {

	/**
	 * To check if only customer has access
	 * 
	 * @param loginCredentials
	 * @throws CredentialsNotValidException
	 */
	void checker(LoginCredentials loginCredentials) throws CredentialsNotValidException;

	/**
	 * To fetch the details of the particular customer who is logged in
	 * 
	 * @param credentials
	 * @return CustomerData
	 * @throws SQLException
	 */
	CustomerData fetchCustomerDetails(LoginCredentials credentials) throws SQLException;

	/**
	 * To update the details of customer
	 * 
	 * @param customerData
	 * @param customerId
	 * @param result
	 * @throws SQLException,
	 * @throws InvalidFieldException
	 */
	void updateCustomer(CustomerData customerData, int customerId, BindingResult result)
			throws SQLException, InvalidFieldException;

	/**
	 * To fetch all the feedback Status available
	 * 
	 * @return List
	 * @throws SQLException,
	 */
	List<FeedbackStatus> fetchFeedbackStatus() throws SQLException;

	/**
	 * To add new feedback
	 * 
	 * @param feedback
	 * @param result
	 * @param credentials
	 * @throws SQLException,
	 * @throws InvalidFieldException
	 */
	void addFeedback(Feedback feedback, BindingResult result, LoginCredentials credentials)
			throws SQLException, InvalidFieldException;

	/**
	 * To calculate total amount of the items available in cart
	 * 
	 * @return int
	 * @throws SQLException,
	 */
	int calculate() throws SQLException;

	/**
	 * To add new order and new order items
	 * 
	 * @param credentials
	 * @throws SQLException,
	 */
	void addOrder(LoginCredentials credentials) throws SQLException;

	/**
	 * @param pizza
	 * @param result
	 * @throws SQLException,
	 * @throws InvalidFieldException To add the items to the cart list
	 */
	List<PizzaOrder> addItem(PizzaOrder pizza, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 * To deduct the amount according to the discount applied for the coupon
	 * 
	 * @param pizzaOrder
	 * @return int
	 * @throws SQLException,
	 * @throws ZeroAmountException
	 */
	int discountPrice(PizzaOrder pizzaOrder) throws SQLException, ZeroAmountException;

	/**
	 * To add new payment
	 * 
	 * @param payment
	 * @param result
	 * @param credentials
	 * @throws SQLException,
	 * @throws InvalidFieldException
	 */
	void addPayment(Payment payment, BindingResult result, LoginCredentials credentials)
			throws SQLException, InvalidFieldException;

}