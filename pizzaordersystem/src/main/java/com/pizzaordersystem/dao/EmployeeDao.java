package com.pizzaordersystem.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.pizzaordersystem.model.Coupon;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Employee;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Order;
import com.pizzaordersystem.model.OrderStatus;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PaymentModes;
import com.pizzaordersystem.model.PizzaMenu;

public interface EmployeeDao {

	List<Order> getOrders(List<Order> orderList) throws SQLException;

	List<Order> getAllOrders(List<Order> orderList) throws SQLException;

	List<OrderStatus> getOrderStatus(List<OrderStatus> orderStatusList) throws SQLException;

	List<Order> getOrdersByStatusType(List<Order> orderList, String statusType) throws SQLException;

	List<Order> getOrdersByDate(List<Order> orderList, Date date) throws SQLException;

	List<CustomerData> getCustomerData(List<CustomerData> customerDataList) throws SQLException;

	List<Feedback> getFeedback(List<Feedback> feedbackList) throws SQLException;

	List<PizzaMenu> getPizza(List<PizzaMenu> pizzaList) throws SQLException;

	void addPizza(PizzaMenu pizzaMenu) throws SQLException;

	PizzaMenu getPizza(int pizzaId) throws SQLException;

	void updatePizza(PizzaMenu pizzaMenu) throws SQLException;

	void deletePizza(int pizzaId) throws SQLException;

	Employee getEmployee(LoginCredentials loginCredentials) throws SQLException;

	List<Coupon> getCoupons(List<Coupon> couponList) throws SQLException;

	void addCoupon(Coupon coupon) throws SQLException;

	Coupon getcoupon(int couponId) throws SQLException;

	void updateCoupon(Coupon coupon) throws SQLException;

	void deleteCoupon(int couponId) throws SQLException;

	List<Payment> getPayments(List<Payment> paymentList) throws SQLException;

	List<PaymentModes> getPaymentModes(List<PaymentModes> paymentModeList) throws SQLException;

	List<Payment> getPaymentByMode(List<Payment> paymentList, String paymentMode) throws SQLException;

	void updateEmployee(Employee employee, int employeeId) throws SQLException;

}