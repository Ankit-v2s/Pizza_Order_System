package com.pizzaordersystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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

public interface PizzaDao {

	Connection getConnection() throws ClassNotFoundException;

	List<LoginCredentials> login(List<LoginCredentials> credentialList) throws SQLException;

	List<Order> getOrders(List<Order> orderList) throws SQLException;

	List<CustomerData> getCustomerData(List<CustomerData> customerDataList) throws SQLException;

	List<Feedback> getFeedback(List<Feedback> feedbackList) throws SQLException;

	List<PizzaMenu> getPizza(List<PizzaMenu> pizzaList) throws SQLException;

	Employee getEmployee(LoginCredentials loginCredentials) throws SQLException;

	CustomerData getCustomer(LoginCredentials loginCredentials) throws SQLException;

	List<Coupon> getCoupons(List<Coupon> couponList) throws SQLException;

	List<Order> getAllOrders(List<Order> orderList) throws SQLException;

	List<Payment> getPayments(List<Payment> paymentList) throws SQLException;

	List<OrderStatus> getOrderStatus(List<OrderStatus> orderStatusList) throws SQLException;

	List<PaymentModes> getPaymentModes(List<PaymentModes> paymentModeList) throws SQLException;

	List<FeedbackStatus> getFeedbackStatus(List<FeedbackStatus> feedbackStatusList) throws SQLException;

	PizzaMenu getPizza(int pizzaId) throws SQLException;

	void addPizza(PizzaMenu pizzaMenu) throws SQLException;

	void addCustomer(RegisterDetails details) throws SQLException;

	void deletePizza(int pizzaId) throws SQLException;

	Coupon getcoupon(int couponId) throws SQLException;

	void addCoupon(Coupon coupon) throws SQLException;

	void deleteCoupon(int couponId) throws SQLException;

	List<Order> getOrdersByStatusType(List<Order> orderList, String statusType) throws SQLException;

	List<Order> getOrdersByDate(List<Order> orderList, Date date) throws SQLException;

	List<Payment> getPaymentByMode(List<Payment> paymentList, String paymentMode) throws SQLException;

	void updatePizza(PizzaMenu pizzaMenu) throws SQLException;

	void updateCoupon(Coupon coupon) throws SQLException;

	List<City> getcity(List<City> cityList) throws SQLException;

	void updateEmployee(Employee employee,int employeeId) throws SQLException;

	void updateCustomer(CustomerData customerData, int customerId) throws SQLException;

	void addFeedback(Feedback feedback, LoginCredentials loginCredentials) throws SQLException;

	int calculateAmount() throws SQLException;

	void addOrder(LoginCredentials loginCredentials) throws SQLException;

	void addItem(List<PizzaOrder> cart) throws SQLException;

	void addPayment(LoginCredentials credentials, Payment payment) throws SQLException;

	int discountPrice(PizzaOrder pizzaOrder) throws SQLException;

	void close();

}