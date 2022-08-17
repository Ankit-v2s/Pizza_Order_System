package com.pizzaordersystem.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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

public interface PizzaService {
	
	void createConnection() throws ClassNotFoundException;

	String credentialChecker(LoginCredentials loginCredentials) throws SQLException, ClassNotFoundException,CredentialCheckerException;

	List<Order> fetchOrders() throws SQLException, ClassNotFoundException;

	List<Feedback> fetchFeedback() throws SQLException, ClassNotFoundException;

	List<CustomerData> fetchCustomer() throws SQLException, ClassNotFoundException;

	List<PizzaMenu> fetchPizzaMenu() throws SQLException, ClassNotFoundException;

	Employee fetchEmployee() throws SQLException;

	List<Coupon> fetchCoupons() throws SQLException, ClassNotFoundException;

	List<Order> fetchAllOrders() throws SQLException, ClassNotFoundException;

	List<Payment> fetchPayments() throws SQLException, ClassNotFoundException;

	CustomerData fetchCustomerDetails() throws SQLException;

	List<FeedbackStatus> fetchFeedbackStatus() throws SQLException, ClassNotFoundException;

	List<OrderStatus> fetchOrderStatus() throws SQLException, ClassNotFoundException;

	List<PaymentModes> fetchPaymentModes() throws SQLException, ClassNotFoundException;

	PizzaMenu fetchPizza(int pizzaId) throws ClassNotFoundException, SQLException;

	void addEditPizza(PizzaMenu pizzaMenu) throws ClassNotFoundException, SQLException;

	void addCustomer(RegisterDetails details) throws ClassNotFoundException, SQLException;

	void deletePizza(int pizzaId) throws ClassNotFoundException, SQLException;

	Coupon fetchCoupon(int couponId) throws ClassNotFoundException, SQLException;

	void addEditCoupon(Coupon coupon) throws ClassNotFoundException, SQLException;

	void deleteCoupon(int couponId) throws ClassNotFoundException, SQLException;

	List<Order> fetchOrdersByStatusType(String statusType) throws SQLException, ClassNotFoundException;

	List<Order> fetchOrdersByDate(Date date) throws SQLException, ClassNotFoundException;

	List<Payment> fetchPaymentByMode(String paymentMode) throws SQLException, ClassNotFoundException;

	List<City> fetchCity() throws SQLException, ClassNotFoundException;

	void updateEmployee(Employee employee) throws ClassNotFoundException, SQLException;

	void updateCustomer(CustomerData customerData) throws ClassNotFoundException, SQLException;

	City fetchCityDetails(String city) throws ClassNotFoundException, SQLException;

	void addFeedback(Feedback feedback) throws ClassNotFoundException, SQLException;

	int orderPizza() throws ClassNotFoundException, SQLException;

	void addOrder() throws ClassNotFoundException, SQLException;

	void addItem(PizzaOrder pizza) throws ClassNotFoundException, SQLException;

	int discountPrice(PizzaOrder pizzaOrder) throws ClassNotFoundException, SQLException;

	void addPayment(Payment payment) throws ClassNotFoundException, SQLException;

	void logout();

}