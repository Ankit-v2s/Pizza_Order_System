package com.pizzaordersystem.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.CustomerDao;
import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.exception.ZeroAmountException;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.service.CustomerService;

/**
 * @author Ankit Madhavi
 *
 */
@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PizzaServiceImplementation pizzaServiceImplementation;
	
	private static final String COUPON_NOT_APLLICABLE = "Coupon not apllicable";
	
	private static final String NO_ACCESS_TO_THIS_PAGE = "No Access to this Page";
	
	private static final String CUSTOMER = "customer";

	/**
	 * To check if only customer has access
	 * 
	 * @param loginCredentials
	 * @throws CredentialsNotValidException
	 */
	@Override
	public void checker(LoginCredentials loginCredentials) throws CredentialsNotValidException {
		if (!loginCredentials.getRoles().equals(CUSTOMER)) {
			throw new CredentialsNotValidException(NO_ACCESS_TO_THIS_PAGE);
		}
	}

	/**
	 * To fetch the details of the particular customer who is logged in
	 * 
	 * @param credentials
	 * @return CustomerData
	 * @throws SQLException
	 */
	@Override
	public CustomerData fetchCustomerDetails(LoginCredentials credentials) throws SQLException {
		return customerDao.getCustomer(credentials);
	}

	/**
	 * To update the details of customer
	 * 
	 * @param customerData
	 * @param customerId
	 * @param result
	 * @throws SQLException,
	 * @throws InvalidFieldException
	 */
	@Override
	public void updateCustomer(CustomerData customerData, int customerId, BindingResult result)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			customerDao.updateCustomer(customerData, customerId);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 * To fetch all the feedback Status available
	 * 
	 * @return List
	 * @throws SQLException,
	 */
	@Override
	public List<FeedbackStatus> fetchFeedbackStatus() throws SQLException {
		List<FeedbackStatus> feedbackStatusList = new ArrayList<>();
		return customerDao.getFeedbackStatus(feedbackStatusList);
	}

	/**
	 * To add new feedback
	 * 
	 * @param feedback
	 * @param result
	 * @param credentials
	 * @throws SQLException,
	 * @throws InvalidFieldException
	 */
	@Override
	public void addFeedback(Feedback feedback, BindingResult result, LoginCredentials credentials)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			customerDao.addFeedback(feedback, credentials);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 * To calculate total amount of the items available in cart
	 * 
	 * @return int
	 * @throws SQLException,
	 */
	@Override
	public int calculate() throws SQLException {
		return customerDao.calculateAmount();
	}

	/**
	 * To add new order and new order items
	 * 
	 * @param credentials
	 * @throws SQLException,
	 */
	@Override
	public void addOrder(LoginCredentials credentials) throws SQLException {
		customerDao.addOrder(credentials);
		customerDao.addItem(pizzaServiceImplementation.getCartList());
	}

	/**
	 * To add the items to the cart list
	 * 
	 * @param pizza
	 * @param result
	 * @throws SQLException,
	 * @throws InvalidFieldException
	 */
	@Override
	public List<PizzaOrder> addItem(PizzaOrder pizza, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			PizzaServiceImplementation.cart.add(pizza);
			return PizzaServiceImplementation.cart;
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 * To deduct the amount according to the discount applied for the coupon
	 * 
	 * @param pizzaOrder
	 * @return int
	 * @throws SQLException,
	 * @throws ZeroAmountException
	 */
	@Override
	public int discountPrice(PizzaOrder pizzaOrder) throws SQLException, ZeroAmountException {
		pizzaOrder.setAmount(pizzaOrder.getAmount() - customerDao.discountPrice(pizzaOrder));
		if (pizzaOrder.getAmount() < 0) {
			throw new ZeroAmountException(COUPON_NOT_APLLICABLE);
		}
		pizzaOrder.setAmount(pizzaOrder.getAmount());
		return pizzaOrder.getAmount();
	}

	/**
	 * To add new payment
	 * 
	 * @param payment
	 * @param result
	 * @param credentials
	 * @throws SQLException,
	 * @throws InvalidFieldException
	 */
	@Override
	public void addPayment(Payment payment, BindingResult result, LoginCredentials credentials)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			PizzaServiceImplementation.cart.clear();
			customerDao.addPayment(credentials, payment);
		} else {
			throw new InvalidFieldException(result);
		}
	}
}
