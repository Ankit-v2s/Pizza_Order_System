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
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.pizzaordersystem.dao.PizzaDao;
import com.pizzaordersystem.exception.CredentialCheckerException;
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

	public void createConnection() throws ClassNotFoundException {
		pizzaDaoImplementation.getConnection();
	}

	@Override
	public String credentialChecker(LoginCredentials loginCredentials, BindingResult result)
			throws SQLException, ClassNotFoundException, CredentialCheckerException, MethodArgumentNotValidException {
		List<LoginCredentials> credentialList = new ArrayList<>();
		this.connection = pizzaDaoImplementation.getConnection();
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
		throw new MethodArgumentNotValidException(null, result);
	}

	@Override
	public List<Order> fetchOrders() throws SQLException, ClassNotFoundException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrders(orderList);
	}

	@Override
	public List<Feedback> fetchFeedback() throws SQLException, ClassNotFoundException {
		List<Feedback> feedbackList = new ArrayList<>();
		return pizzaDaoImplementation.getFeedback(feedbackList);
	}

	@Override
	public List<CustomerData> fetchCustomer() throws SQLException, ClassNotFoundException {
		List<CustomerData> customerDataList = new ArrayList<>();
		return pizzaDaoImplementation.getCustomerData(customerDataList);
	}

	@Override
	public List<PizzaMenu> fetchPizzaMenu() throws SQLException, ClassNotFoundException {
		List<PizzaMenu> pizzaList = new ArrayList<>();
		return pizzaDaoImplementation.getPizza(pizzaList);
	}

	@Override
	public Employee fetchEmployee() throws SQLException {
		return pizzaDaoImplementation.getEmployee(loginCredentials);
	}

	@Override
	public List<Coupon> fetchCoupons() throws SQLException, ClassNotFoundException {
		List<Coupon> couponList = new ArrayList<>();
		return pizzaDaoImplementation.getCoupons(couponList);
	}

	@Override
	public List<Order> fetchAllOrders() throws SQLException, ClassNotFoundException {
		List<Order> orderList = new ArrayList<>();
		pizzaDaoImplementation.getConnection();
		return pizzaDaoImplementation.getAllOrders(orderList);
	}

	@Override
	public List<Payment> fetchPayments() throws SQLException, ClassNotFoundException {
		List<Payment> paymentList = new ArrayList<>();
		return pizzaDaoImplementation.getPayments(paymentList);
	}

	@Override
	public CustomerData fetchCustomerDetails() throws SQLException {
		return pizzaDaoImplementation.getCustomer(loginCredentials);
	}

	@Override
	public List<FeedbackStatus> fetchFeedbackStatus() throws SQLException, ClassNotFoundException {
		List<FeedbackStatus> feedbackStatusList = new ArrayList<>();
		return pizzaDaoImplementation.getFeedbackStatus(feedbackStatusList);
	}

	@Override
	public List<OrderStatus> fetchOrderStatus() throws SQLException, ClassNotFoundException {
		List<OrderStatus> orderStatusList = new ArrayList<>();
		return pizzaDaoImplementation.getOrderStatus(orderStatusList);
	}

	@Override
	public List<PaymentModes> fetchPaymentModes() throws SQLException, ClassNotFoundException {
		List<PaymentModes> paymentModeList = new ArrayList<>();
		return pizzaDaoImplementation.getPaymentModes(paymentModeList);
	}

	@Override
	public PizzaMenu fetchPizza(int pizzaId) throws ClassNotFoundException, SQLException {
		return pizzaDaoImplementation.getPizza(pizzaId);
	}

	@Override
	public void addEditPizza(PizzaMenu pizzaMenu) throws ClassNotFoundException, SQLException {
		if (pizzaMenu.getPizzaId() == 0) {
			pizzaDaoImplementation.addPizza(pizzaMenu);
		} else {
			pizzaDaoImplementation.updatePizza(pizzaMenu);
		}
	}

	@Override
	public void addCustomer(@Valid RegisterDetails details,BindingResult result) throws ClassNotFoundException, SQLException, MethodArgumentNotValidException {
		if(!result.hasErrors()) {
			pizzaDaoImplementation.addCustomer(details);
		}else {
			throw new MethodArgumentNotValidException(null, result);
		}
	}

	@Override
	public void deletePizza(int pizzaId) throws ClassNotFoundException, SQLException {
		pizzaDaoImplementation.deletePizza(pizzaId);
	}

	@Override
	public Coupon fetchCoupon(int couponId) throws ClassNotFoundException, SQLException {
		return pizzaDaoImplementation.getcoupon(couponId);
	}

	@Override
	public void addEditCoupon(Coupon coupon) throws ClassNotFoundException, SQLException {
		if (coupon.getCouponId() == 0) {
			pizzaDaoImplementation.addCoupon(coupon);
		} else {
			pizzaDaoImplementation.updateCoupon(coupon);
		}
	}

	@Override
	public void deleteCoupon(int couponId) throws ClassNotFoundException, SQLException {
		pizzaDaoImplementation.deleteCoupon(couponId);
	}

	@Override
	public List<Order> fetchOrdersByStatusType(String statusType) throws SQLException, ClassNotFoundException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrdersByStatusType(orderList, statusType);
	}

	@Override
	public List<Order> fetchOrdersByDate(Date date) throws SQLException, ClassNotFoundException {
		List<Order> orderList = new ArrayList<>();
		return pizzaDaoImplementation.getOrdersByDate(orderList, date);
	}

	@Override
	public List<Payment> fetchPaymentByMode(String paymentMode) throws SQLException, ClassNotFoundException {
		List<Payment> paymentList = new ArrayList<>();
		return pizzaDaoImplementation.getPaymentByMode(paymentList, paymentMode);
	}

	@Override
	public List<City> fetchCity() throws SQLException, ClassNotFoundException {
		List<City> cityList = new ArrayList<>();
		return pizzaDaoImplementation.getcity(cityList);
	}

	@Override
	public void updateEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		pizzaDaoImplementation.updateEmployee(employee);
	}

	@Override
	public void updateCustomer(CustomerData customerData) throws ClassNotFoundException, SQLException {
		pizzaDaoImplementation.updateCustomer(customerData);
	}

	@Override
	public City fetchCityDetails(String city) throws ClassNotFoundException, SQLException {
		for (City cityDetails : fetchCity()) {
			if (cityDetails.getCityName().equals(city)) {
				return cityDetails;
			}
		}
		throw new NullPointerException();
	}

	@Override
	public void addFeedback(Feedback feedback) throws ClassNotFoundException, SQLException {
		pizzaDaoImplementation.addFeedback(feedback, loginCredentials);
	}

	@Override
	public int orderPizza() throws ClassNotFoundException, SQLException {
		return pizzaDaoImplementation.pizzaOrder();
	}

	@Override
	public void addOrder() throws ClassNotFoundException, SQLException {
		pizzaDaoImplementation.addOrder(loginCredentials);
	}

	@Override
	public void addItem(PizzaOrder pizza) throws ClassNotFoundException, SQLException {
		this.pizzaOrder = pizza;
		pizzaDaoImplementation.addItem(pizza);
	}

	@Override
	public int discountPrice(PizzaOrder pizzaOrder) throws ClassNotFoundException, SQLException {
		pizzaOrder.setAmount(pizzaOrder.getAmount() - pizzaDaoImplementation.discountPrice(pizzaOrder));
		this.pizzaOrder.setAmount(pizzaOrder.getAmount());
		return pizzaOrder.getAmount();
	}

	@Override
	public void addPayment(Payment payment) throws ClassNotFoundException, SQLException {
		pizzaDaoImplementation.addPayment(loginCredentials, payment);
	}

	@Override
	public void logout() {
		pizzaDaoImplementation.close();
	}
}
