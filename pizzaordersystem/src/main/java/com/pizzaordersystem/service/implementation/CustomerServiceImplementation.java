package com.pizzaordersystem.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.PizzaDao;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.exception.ZeroAmountException;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.service.CustomerService;

public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private PizzaDao pizzaDaoImplementation;
	@Autowired
	private PizzaServiceImplementation pizzaServiceImplementation;
	
	/**
	 *@return CustomerData
	 *@throws SQLException
	 *To fetch the details of the particular customer who is logged in
	 */
	@Override
	public CustomerData fetchCustomerDetails() throws SQLException {
		return pizzaDaoImplementation.getCustomer(pizzaServiceImplementation.getLoginCredentials());
	}

	/**
	 *@param customerData
	 *@param customerId
	 *@param result
	 *@throws SQLException,
	 *@throws InvalidFieldException
	 *To update the details of customer
	 */
	@Override
	public void updateCustomer(CustomerData customerData, int customerId, BindingResult result)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaDaoImplementation.updateCustomer(customerData,customerId);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 *@return List
	 *@throws SQLException,
	 *To fetch all the feedback Status available
	 */
	@Override
	public List<FeedbackStatus> fetchFeedbackStatus() throws SQLException {
		List<FeedbackStatus> feedbackStatusList = new ArrayList<>();
		return pizzaDaoImplementation.getFeedbackStatus(feedbackStatusList);
	}
	
	/**
	 *@param feedback
	 *@param result
	 *@throws SQLException,
	 *@throws InvalidFieldException
	 *To add new feedback
	 */
	@Override
	public void addFeedback(Feedback feedback, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaDaoImplementation.addFeedback(feedback, pizzaServiceImplementation.getLoginCredentials());
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 *@return int
	 *@throws SQLException,
	 *To calculate total amount of the items available in cart
	 */
	@Override
	public int calculate() throws SQLException {
		return pizzaDaoImplementation.calculateAmount();
	}

	/**
	 *@throws SQLException,
	 *To add new order and new order items
	 */
	@Override
	public void addOrder() throws SQLException {
		pizzaDaoImplementation.addOrder(pizzaServiceImplementation.getLoginCredentials());
		pizzaDaoImplementation.addItem(pizzaServiceImplementation.getCartList());
	}

	/**
	 *@param pizza
	 *@param result
	 *@throws SQLException,
	 *@throws InvalidFieldException
	 *To add the items to the cart list
	 */
	@Override
	public List<PizzaOrder> addItem(PizzaOrder pizza, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaServiceImplementation.setPizzaOrder(pizza);
			pizzaServiceImplementation.getCartList().add(pizza);
			return pizzaServiceImplementation.getCartList();
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 *@param pizzaOrder
	 *@return int
	 *@throws SQLException,
	 *@throws ZeroAmountException
	 *To deduct the amount according to the discount applied for the coupon
	 */
	@Override
	public int discountPrice(PizzaOrder pizzaOrder) throws SQLException, ZeroAmountException {
		pizzaOrder.setAmount(pizzaOrder.getAmount() - pizzaDaoImplementation.discountPrice(pizzaOrder));
		if (pizzaOrder.getAmount() < 0) {
			throw new ZeroAmountException("Coupon not apllicable");
		}
		pizzaServiceImplementation.getPizzaOrder().setAmount(pizzaOrder.getAmount());
		return pizzaOrder.getAmount();
	}

	/**
	 *@param payment
	 *@param result
	 *@throws SQLException,
	 *@throws InvalidFieldException
	 *To add new payment
	 */
	@Override
	public void addPayment(Payment payment, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaServiceImplementation.getCartList().clear();
			pizzaDaoImplementation.addPayment(pizzaServiceImplementation.getLoginCredentials(), payment);
		} else {
			throw new InvalidFieldException(result);
		}
	}
}
