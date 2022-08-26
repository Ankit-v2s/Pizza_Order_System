package com.pizzaordersystem.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;

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

public interface PizzaService {

	void createConnection() throws ClassNotFoundException;

	String credentialChecker(@Valid LoginCredentials loginCredentials, BindingResult result)
			throws SQLException, CredentialCheckerException, InvalidFieldException;

	List<Order> fetchOrders() throws SQLException;

	List<Feedback> fetchFeedback() throws SQLException;

	List<CustomerData> fetchCustomer() throws SQLException;

	List<PizzaMenu> fetchPizzaMenu() throws SQLException;

	Employee fetchEmployee() throws SQLException;

	List<Coupon> fetchCoupons() throws SQLException;

	List<Order> fetchAllOrders() throws SQLException;

	List<Payment> fetchPayments() throws SQLException;

	CustomerData fetchCustomerDetails() throws SQLException;

	List<FeedbackStatus> fetchFeedbackStatus() throws SQLException;

	List<OrderStatus> fetchOrderStatus() throws SQLException;

	List<PaymentModes> fetchPaymentModes() throws SQLException;

	PizzaMenu fetchPizza(int pizzaId) throws SQLException;

	void addEditPizza(PizzaMenu pizzaMenu, BindingResult result) throws SQLException, InvalidFieldException;

	void addCustomer(RegisterDetails details, BindingResult result) throws SQLException, InvalidFieldException;

	void deletePizza(int pizzaId) throws SQLException;

	Coupon fetchCoupon(int couponId) throws SQLException;

	void addEditCoupon(Coupon coupon, BindingResult result) throws SQLException, InvalidFieldException;

	void deleteCoupon(int couponId) throws SQLException;

	List<Order> fetchOrdersByStatusType(String statusType) throws SQLException;

	List<Order> fetchOrdersByDate(Date date) throws SQLException;

	List<Payment> fetchPaymentByMode(String paymentMode) throws SQLException;

	List<City> fetchCity() throws SQLException;

	void updateEmployee(Employee employee,int employeeId, BindingResult result) throws SQLException, InvalidFieldException;

	void updateCustomer(CustomerData customerData, int customerId, BindingResult result) throws SQLException, InvalidFieldException;

	City fetchCityDetails(String city) throws SQLException;

	void addFeedback(Feedback feedback, BindingResult result) throws SQLException, InvalidFieldException;

	List<PizzaOrder> getCartList();
	
	int orderPizza() throws SQLException;

	void addOrder() throws SQLException;

	List<PizzaOrder> addItem(PizzaOrder pizza, BindingResult result) throws SQLException, InvalidFieldException;

	int discountPrice(PizzaOrder pizzaOrder) throws SQLException, ZeroAmountException;

	void addPayment(Payment payment, BindingResult result) throws SQLException, InvalidFieldException;

	void logout();

}