package com.pizzaordersystem.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.CustomerDao;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.exception.ZeroAmountException;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.service.CustomerService;

@Service
public class CustomerServiceImplementation extends PizzaServiceImplementation implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	/**
	 *@return CustomerData
	 *@throws SQLException
	 *To fetch the details of the particular customer who is logged in
	 */
	@Override
	public CustomerData fetchCustomerDetails() throws SQLException {
		return customerDao.getCustomer(loginCredentials);
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
			customerDao.updateCustomer(customerData,customerId);
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
		return customerDao.getFeedbackStatus(feedbackStatusList);
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
			customerDao.addFeedback(feedback, loginCredentials);
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
		return customerDao.calculateAmount();
	}

	/**
	 *@throws SQLException,
	 *To add new order and new order items
	 */
	@Override
	public void addOrder() throws SQLException {
		customerDao.addOrder(loginCredentials);
		customerDao.addItem(getCartList());
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
			PizzaServiceImplementation.pizzaOrder = pizza;
			cart.add(pizza);
			return cart;
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
		pizzaOrder.setAmount(pizzaOrder.getAmount() - customerDao.discountPrice(pizzaOrder));
		if (pizzaOrder.getAmount() < 0) {
			throw new ZeroAmountException("Coupon not apllicable");
		}
		pizzaOrder.setAmount(pizzaOrder.getAmount());
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
			cart.clear();
			customerDao.addPayment(loginCredentials, payment);
		} else {
			throw new InvalidFieldException(result);
		}
	}
}
