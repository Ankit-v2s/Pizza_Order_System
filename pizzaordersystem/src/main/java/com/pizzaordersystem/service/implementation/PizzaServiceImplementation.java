package com.pizzaordersystem.service.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.PizzaDao;
import com.pizzaordersystem.exception.*;
import com.pizzaordersystem.model.*;
import com.pizzaordersystem.service.PizzaService;

/**
 * @author Ankit Madhavi
 *
 */
@Service
public class PizzaServiceImplementation implements PizzaService {

	@Autowired
	private PizzaDao pizzaDaoImplementation;
	private LoginCredentials loginCredentials;
	private PizzaOrder pizzaOrder;
	Connection connection;
	private List<PizzaOrder> cart = new ArrayList<>();
	
	/**
	 *@return List
	 *getter for the List cart
	 */
	@Override
	public List<PizzaOrder> getCartList() {
		return cart;
	}

	/**
	 *@throws ClassNotFoundException
	 *To make a connection database
	 */
	public void createConnection() throws ClassNotFoundException {
		this.connection = pizzaDaoImplementation.getConnection();
	}

//	==========Login==========
	/**
	 *@param loginCredentials
	 *@param result
	 *@return String
	 *@throws SQLException
	 *@throws CredentialCheckerException
	 *@throws InvalidFieldException
	 *To check the credentials
	 */
	@Override
	public String credentialChecker(LoginCredentials loginCredentials, BindingResult result)
			throws SQLException, CredentialCheckerException, InvalidFieldException {
		List<LoginCredentials> credentialList = new ArrayList<>();
		if (!result.hasErrors()) {
			for (LoginCredentials credentials : pizzaDaoImplementation.login(credentialList)) {
				if (credentials.getUserName().equals(loginCredentials.getUserName())
						&& credentials.getPassword().equals(loginCredentials.getPassword())) {
					this.loginCredentials = credentials;
					if (credentials.getEmployeeId() != 0) {
						return "employeehome";
					} else if (credentials.getCustomerId() != 0) {
						return "customerhome";
					}
				}
			}
			throw new CredentialCheckerException("Invalid Credentials");
		}
		throw new InvalidFieldException(result);
	}

//	==========SignUp==========
	/**
	 *@param details
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To add new customer 
	 */
	@Override
	public void addCustomer(@Valid RegisterDetails details, BindingResult result)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaDaoImplementation.addCustomer(details);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the cities in list
	 */
	@Override
	public List<City> fetchCity() throws SQLException {
		List<City> cityList = new ArrayList<>();
		return pizzaDaoImplementation.getcity(cityList);
	}

	/**
	 *@param city
	 *@return City
	 *@throws SQLException
	 *To fetch the details of the particular city
	 */
	@Override
	public City fetchCityDetails(String city) throws SQLException {
		for (City cityDetails : fetchCity()) {
			if (cityDetails.getCityName().equals(city)) {
				return cityDetails;
			}
		}
		throw new NullPointerException();
	}

//	==========Admin==========
	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of orders for the present day;
	 */
	@Override
	public List<Order> fetchOrders() throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrders(orderList);
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch list of all the available orders
	 */
	@Override
	public List<Order> fetchAllOrders() throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getAllOrders(orderList);
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch list of order status available
	 */
	@Override
	public List<OrderStatus> fetchOrderStatus() throws SQLException {
		List<OrderStatus> orderStatusList = new ArrayList<>();
		return pizzaDaoImplementation.getOrderStatus(orderStatusList);
	}

	/**
	 *@param statusType
	 *@return List
	 *@throws SQLException
	 *To fetch the list of orders according to the order status selected
	 */
	@Override
	public List<Order> fetchOrdersByStatusType(String statusType) throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrdersByStatusType(orderList, statusType);
	}

	/**
	 *@param date
	 *@return List
	 *@throws SQLException
	 *To fetch the list of orders according to the date selected
	 */
	@Override
	public List<Order> fetchOrdersByDate(Date date) throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrdersByDate(orderList, date);
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the feedbacks available
	 */
	@Override
	public List<Feedback> fetchFeedback() throws SQLException {
		List<Feedback> feedbackList = new ArrayList<>();
		return pizzaDaoImplementation.getFeedback(feedbackList);
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the customers available
	 */
	@Override
	public List<CustomerData> fetchCustomer() throws SQLException {
		List<CustomerData> customerDataList = new ArrayList<>();
		return pizzaDaoImplementation.getCustomerData(customerDataList);
	}

	/**
	 *@return Employee
	 *@throws SQLException
	 *To fetch the data of the logged in employee
	 */
	@Override
	public Employee fetchEmployee() throws SQLException {
		return pizzaDaoImplementation.getEmployee(loginCredentials);
	}

	/**
	 *@param employee
	 *@param employeeId
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To update the details of the employee
	 */
	@Override
	public void updateEmployee(Employee employee, int employeeId, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaDaoImplementation.updateEmployee(employee,employeeId);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the payments available
	 */
	@Override
	public List<Payment> fetchPayments() throws SQLException {
		List<Payment> paymentList = new ArrayList<>();
		return pizzaDaoImplementation.getPayments(paymentList);
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the payment modes available
	 */
	@Override
	public List<PaymentModes> fetchPaymentModes() throws SQLException {
		List<PaymentModes> paymentModeList = new ArrayList<>();
		return pizzaDaoImplementation.getPaymentModes(paymentModeList);
	}

	/**
	 *@param paymentMode
	 *@return List
	 *@throws SQLException
	 *To fetch the list of payments according to payment modes
	 */
	@Override
	public List<Payment> fetchPaymentByMode(String paymentMode) throws SQLException {
		List<Payment> paymentList = new ArrayList<>();
		return pizzaDaoImplementation.getPaymentByMode(paymentList, paymentMode);
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch all the list of pizza available
	 */
	@Override
	public List<PizzaMenu> fetchPizzaMenu() throws SQLException {
		List<PizzaMenu> pizzaList = new ArrayList<>();
		return pizzaDaoImplementation.getPizza(pizzaList);
	}

	/**
	 *@param pizzaMenu
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To add new pizza or edit the available pizza
	 */
	@Override
	public void addEditPizza(PizzaMenu pizzaMenu, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			if (pizzaMenu.getPizzaId() == 0) {
				pizzaDaoImplementation.addPizza(pizzaMenu);
			} else {
				pizzaDaoImplementation.updatePizza(pizzaMenu);
			}
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 *@param pizzaId
	 *@return PizzaMenu
	 *@throws SQLException
	 *To fetch the details of the particular pizza 
	 */
	@Override
	public PizzaMenu fetchPizza(int pizzaId) throws SQLException {
		return pizzaDaoImplementation.getPizza(pizzaId);
	}

	/**
	 *@param pizzaId
	 *@throws SQLException
	 *To delete the particular pizza
	 */
	@Override
	public void deletePizza(int pizzaId) throws SQLException {
		pizzaDaoImplementation.deletePizza(pizzaId);
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the coupons available
	 */
	@Override
	public List<Coupon> fetchCoupons() throws SQLException {
		List<Coupon> couponList = new ArrayList<>();
		return pizzaDaoImplementation.getCoupons(couponList);
	}

	/**
	 *@param coupon
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To add new coupon or edit the existing coupon
	 */
	@Override
	public void addEditCoupon(Coupon coupon, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			if (coupon.getCouponId() == 0) {
				pizzaDaoImplementation.addCoupon(coupon);
			} else {
				pizzaDaoImplementation.updateCoupon(coupon);
			}
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 *@param couponId
	 *@return Coupon
	 *@throws SQLException
	 *To fetch the details of the particular coupon
	 */
	@Override
	public Coupon fetchCoupon(int couponId) throws SQLException {
		return pizzaDaoImplementation.getcoupon(couponId);
	}

	/**
	 *@param couponId
	 *@throws SQLException
	 *To delete the particular coupon available
	 */
	@Override
	public void deleteCoupon(int couponId) throws SQLException {
		pizzaDaoImplementation.deleteCoupon(couponId);
	}

//	==========Customer==========
	/**
	 *@return CustomerData
	 *@throws SQLException
	 *To fetch the details of the particular customer who is logged in
	 */
	@Override
	public CustomerData fetchCustomerDetails() throws SQLException {
		return pizzaDaoImplementation.getCustomer(loginCredentials);
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
			pizzaDaoImplementation.addFeedback(feedback, loginCredentials);
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
		pizzaDaoImplementation.addOrder(loginCredentials);
		pizzaDaoImplementation.addItem(cart);
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
			this.pizzaOrder = pizza;
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
		pizzaOrder.setAmount(pizzaOrder.getAmount() - pizzaDaoImplementation.discountPrice(pizzaOrder));
		if (pizzaOrder.getAmount() < 0) {
			throw new ZeroAmountException("Coupon not apllicable");
		}
		this.pizzaOrder.setAmount(pizzaOrder.getAmount());
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
			pizzaDaoImplementation.addPayment(loginCredentials, payment);
		} else {
			throw new InvalidFieldException(result);
		}
	}

//	==========Logout==========
	/**
	 *To logout and close the connection with the database
	 */
	@Override
	public void logout() {
		pizzaDaoImplementation.close();
	}
}
