package com.pizzaordersystem.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidFieldException;
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

/**
 * @author Ankit Madhavi
 *
 */
public interface EmployeeService {

	/**
	 * To check if only admin has access
	 * 
	 * @param loginCredentials
	 * @throws CredentialsNotValidException
	 */
	void checker(LoginCredentials loginCredentials) throws CredentialsNotValidException;

	/**
	 * To fetch the list of orders for the present day;
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<Order> fetchOrders() throws SQLException;

	/**
	 * To fetch list of all the available orders
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<Order> fetchAllOrders() throws SQLException;

	/**
	 * To fetch list of order status available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<OrderStatus> fetchOrderStatus() throws SQLException;

	/**
	 * To fetch the list of orders according to the order status selected
	 * 
	 * @param statusType
	 * @return List
	 * @throws SQLException
	 */
	List<Order> fetchOrdersByStatusType(String statusType) throws SQLException;

	/**
	 * To fetch the list of orders according to the date selected
	 * 
	 * @param date
	 * @return List
	 * @throws SQLException
	 */
	List<Order> fetchOrdersByDate(Date date) throws SQLException;

	/**
	 * To fetch the list of all the feedbacks available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<Feedback> fetchFeedback() throws SQLException;

	/**
	 * To fetch the list of all the customers available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<CustomerData> fetchCustomer() throws SQLException;

	/**
	 * To fetch the data of the logged in employee
	 * 
	 * @param credentials
	 * @return Employee
	 * @throws SQLException
	 */
	Employee fetchEmployee(LoginCredentials credentials) throws SQLException;

	/**
	 * To update the details of the employee
	 * 
	 * @param employee
	 * @param employeeId
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	void updateEmployee(Employee employee, int employeeId, BindingResult result)
			throws SQLException, InvalidFieldException;

	/**
	 * To fetch the list of all the payments available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<Payment> fetchPayments() throws SQLException;

	/**
	 * To fetch the list of all the payment modes available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<PaymentModes> fetchPaymentModes() throws SQLException;

	/**
	 * To fetch the list of payments according to payment modes
	 * 
	 * @param paymentMode
	 * @return List
	 * @throws SQLException
	 */
	List<Payment> fetchPaymentByMode(String paymentMode) throws SQLException;

	/**
	 * To fetch all the list of pizza available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<PizzaMenu> fetchPizzaMenu() throws SQLException;

	/**
	 * To add new pizza or edit the available pizza
	 * 
	 * @param pizzaMenu
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	void addEditPizza(PizzaMenu pizzaMenu, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 * To fetch the details of the particular pizza
	 * 
	 * @param pizzaId
	 * @return PizzaMenu
	 * @throws SQLException
	 */
	PizzaMenu fetchPizza(int pizzaId) throws SQLException;

	/**
	 * To delete the particular pizza
	 * 
	 * @param pizzaId
	 * @throws SQLException
	 */
	void deletePizza(int pizzaId) throws SQLException;

	/**
	 * To fetch the list of all the coupons available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<Coupon> fetchCoupons() throws SQLException;

	/**
	 * To add new coupon or edit the existing coupon
	 * 
	 * @param coupon
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	void addEditCoupon(Coupon coupon, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 * To fetch the details of the particular coupon
	 * 
	 * @param couponId
	 * @return Coupon
	 * @throws SQLException
	 */
	Coupon fetchCoupon(int couponId) throws SQLException;

	/**
	 * To delete the particular coupon available
	 * 
	 * @param couponId
	 * @throws SQLException
	 */
	void deleteCoupon(int couponId) throws SQLException;

}