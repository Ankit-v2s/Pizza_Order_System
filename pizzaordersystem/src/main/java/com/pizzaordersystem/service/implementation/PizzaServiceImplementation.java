package com.pizzaordersystem.service.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.PizzaDao;
import com.pizzaordersystem.exception.CredentialCheckerException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.exception.ZeroAmountException;
import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.Coupon;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Employee;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Order;
import com.pizzaordersystem.model.OrderStatus;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PaymentModes;
import com.pizzaordersystem.model.PizzaMenu;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.model.RegisterDetails;
import com.pizzaordersystem.service.PizzaService;

@Service
public class PizzaServiceImplementation implements PizzaService {

	@Autowired
	private PizzaDao pizzaDaoImplementation;
	LoginCredentials loginCredentials;
	PizzaOrder pizzaOrder;
	Connection connection;
	private List<PizzaOrder> cart=new ArrayList<>();

	public void createConnection() throws ClassNotFoundException {
		this.connection = pizzaDaoImplementation.getConnection();
	}

	@Override
	public String credentialChecker(LoginCredentials loginCredentials, BindingResult result, Model model)
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

	@Override
	public List<Order> fetchOrders() throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrders(orderList);
	}

	@Override
	public List<Feedback> fetchFeedback() throws SQLException {
		List<Feedback> feedbackList = new ArrayList<>();
		return pizzaDaoImplementation.getFeedback(feedbackList);
	}

	@Override
	public List<CustomerData> fetchCustomer() throws SQLException {
		List<CustomerData> customerDataList = new ArrayList<>();
		return pizzaDaoImplementation.getCustomerData(customerDataList);
	}

	@Override
	public List<PizzaMenu> fetchPizzaMenu() throws SQLException {
		List<PizzaMenu> pizzaList = new ArrayList<>();
		return pizzaDaoImplementation.getPizza(pizzaList);
	}

	@Override
	public Employee fetchEmployee() throws SQLException {
		return pizzaDaoImplementation.getEmployee(loginCredentials);
	}

	@Override
	public List<Coupon> fetchCoupons() throws SQLException {
		List<Coupon> couponList = new ArrayList<>();
		return pizzaDaoImplementation.getCoupons(couponList);
	}

	@Override
	public List<Order> fetchAllOrders() throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getAllOrders(orderList);
	}

	@Override
	public List<Payment> fetchPayments() throws SQLException {
		List<Payment> paymentList = new ArrayList<>();
		return pizzaDaoImplementation.getPayments(paymentList);
	}

	@Override
	public CustomerData fetchCustomerDetails() throws SQLException {
		return pizzaDaoImplementation.getCustomer(loginCredentials);
	}

	@Override
	public List<FeedbackStatus> fetchFeedbackStatus() throws SQLException {
		List<FeedbackStatus> feedbackStatusList = new ArrayList<>();
		return pizzaDaoImplementation.getFeedbackStatus(feedbackStatusList);
	}

	@Override
	public List<OrderStatus> fetchOrderStatus() throws SQLException {
		List<OrderStatus> orderStatusList = new ArrayList<>();
		return pizzaDaoImplementation.getOrderStatus(orderStatusList);
	}

	@Override
	public List<PaymentModes> fetchPaymentModes() throws SQLException {
		List<PaymentModes> paymentModeList = new ArrayList<>();
		return pizzaDaoImplementation.getPaymentModes(paymentModeList);
	}

	@Override
	public PizzaMenu fetchPizza(int pizzaId) throws SQLException {
		return pizzaDaoImplementation.getPizza(pizzaId);
	}

	@Override
	public void addEditPizza(PizzaMenu pizzaMenu,BindingResult result) throws SQLException, InvalidFieldException {
		if(!result.hasErrors()) {
			if (pizzaMenu.getPizzaId() == 0) {
				pizzaDaoImplementation.addPizza(pizzaMenu);
			} else {
				pizzaDaoImplementation.updatePizza(pizzaMenu);
			}
		} else {
			throw new InvalidFieldException(result);
		}
	}

	@Override
	public void addCustomer(@Valid RegisterDetails details, BindingResult result)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaDaoImplementation.addCustomer(details);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	@Override
	public void deletePizza(int pizzaId) throws SQLException {
		pizzaDaoImplementation.deletePizza(pizzaId);
	}

	@Override
	public Coupon fetchCoupon(int couponId) throws SQLException {
		return pizzaDaoImplementation.getcoupon(couponId);
	}

	@Override
	public void addEditCoupon(Coupon coupon,BindingResult result) throws SQLException, InvalidFieldException {
		if(!result.hasErrors()) {
			if (coupon.getCouponId() == 0) {
				pizzaDaoImplementation.addCoupon(coupon);
			} else {
				pizzaDaoImplementation.updateCoupon(coupon);
			}
		} else {
			throw new InvalidFieldException(result);
		}
	}

	@Override
	public void deleteCoupon(int couponId) throws SQLException {
		pizzaDaoImplementation.deleteCoupon(couponId);
	}

	@Override
	public List<Order> fetchOrdersByStatusType(String statusType) throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrdersByStatusType(orderList, statusType);
	}

	@Override
	public List<Order> fetchOrdersByDate(Date date) throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrdersByDate(orderList, date);
	}

	@Override
	public List<Payment> fetchPaymentByMode(String paymentMode) throws SQLException {
		List<Payment> paymentList = new ArrayList<>();
		return pizzaDaoImplementation.getPaymentByMode(paymentList, paymentMode);
	}

	@Override
	public List<City> fetchCity() throws SQLException {
		List<City> cityList = new ArrayList<>();
		return pizzaDaoImplementation.getcity(cityList);
	}

	@Override
	public void updateEmployee(Employee employee,BindingResult result) throws SQLException, InvalidFieldException {
		if(!result.hasErrors()) {
			pizzaDaoImplementation.updateEmployee(employee);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	@Override
	public void updateCustomer(CustomerData customerData, BindingResult result)
			throws SQLException, InvalidFieldException {
		if(!result.hasErrors()) {
			pizzaDaoImplementation.updateCustomer(customerData);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	@Override
	public City fetchCityDetails(String city) throws SQLException {
		for (City cityDetails : fetchCity()) {
			if (cityDetails.getCityName().equals(city)) {
				return cityDetails;
			}
		}
		throw new NullPointerException();
	}

	@Override
	public void addFeedback(Feedback feedback,BindingResult result) throws SQLException,InvalidFieldException {
		if(!result.hasErrors()) {
			pizzaDaoImplementation.addFeedback(feedback, loginCredentials);
		} else {
			throw new InvalidFieldException(result);
		}
	}
	
	@Override
	public List<PizzaOrder> getCartList() {
		return cart;
	}

	@Override
	public int orderPizza() throws SQLException {
		return pizzaDaoImplementation.pizzaOrder();
	}

	@Override
	public void addOrder() throws SQLException {
			pizzaDaoImplementation.addOrder(loginCredentials);
			pizzaDaoImplementation.addItem(cart);
	}

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

	@Override
	public int discountPrice(PizzaOrder pizzaOrder) throws SQLException, ZeroAmountException {
		pizzaOrder.setAmount(pizzaOrder.getAmount() - pizzaDaoImplementation.discountPrice(pizzaOrder));
		if(pizzaOrder.getAmount()<0) {
			throw new ZeroAmountException("Coupon not apllicable");
		}
		this.pizzaOrder.setAmount(pizzaOrder.getAmount());
		return pizzaOrder.getAmount();
	}

	@Override
	public void addPayment(Payment payment, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			cart.clear();
			pizzaDaoImplementation.addPayment(loginCredentials, payment);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	@Override
	public void logout() {
		pizzaDaoImplementation.close();
	}
}
