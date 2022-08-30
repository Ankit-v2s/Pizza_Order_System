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
import com.pizzaordersystem.model.Order;
import com.pizzaordersystem.model.OrderStatus;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PaymentModes;
import com.pizzaordersystem.model.PizzaMenu;

public interface EmployeeService {

	/**
	 *@throws CredentialsNotValidException
	 *To check if only admin has access
	 */
	void checker() throws CredentialsNotValidException;
	
	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of orders for the present day;
	 */
	List<Order> fetchOrders() throws SQLException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch list of all the available orders
	 */
	List<Order> fetchAllOrders() throws SQLException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch list of order status available
	 */
	List<OrderStatus> fetchOrderStatus() throws SQLException;

	/**
	 *@param statusType
	 *@return List
	 *@throws SQLException
	 *To fetch the list of orders according to the order status selected
	 */
	List<Order> fetchOrdersByStatusType(String statusType) throws SQLException;

	/**
	 *@param date
	 *@return List
	 *@throws SQLException
	 *To fetch the list of orders according to the date selected
	 */
	List<Order> fetchOrdersByDate(Date date) throws SQLException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the feedbacks available
	 */
	List<Feedback> fetchFeedback() throws SQLException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the customers available
	 */
	List<CustomerData> fetchCustomer() throws SQLException;

	/**
	 *@return Employee
	 *@throws SQLException
	 *To fetch the data of the logged in employee
	 */
	Employee fetchEmployee() throws SQLException;

	/**
	 *@param employee
	 *@param employeeId
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To update the details of the employee
	 */
	void updateEmployee(Employee employee, int employeeId, BindingResult result)
			throws SQLException, InvalidFieldException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the payments available
	 */
	List<Payment> fetchPayments() throws SQLException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the payment modes available
	 */
	List<PaymentModes> fetchPaymentModes() throws SQLException;

	/**
	 *@param paymentMode
	 *@return List
	 *@throws SQLException
	 *To fetch the list of payments according to payment modes
	 */
	List<Payment> fetchPaymentByMode(String paymentMode) throws SQLException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch all the list of pizza available
	 */
	List<PizzaMenu> fetchPizzaMenu() throws SQLException;

	/**
	 *@param pizzaMenu
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To add new pizza or edit the available pizza
	 */
	void addEditPizza(PizzaMenu pizzaMenu, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 *@param pizzaId
	 *@return PizzaMenu
	 *@throws SQLException
	 *To fetch the details of the particular pizza 
	 */
	PizzaMenu fetchPizza(int pizzaId) throws SQLException;

	/**
	 *@param pizzaId
	 *@throws SQLException
	 *To delete the particular pizza
	 */
	void deletePizza(int pizzaId) throws SQLException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the list of all the coupons available
	 */
	List<Coupon> fetchCoupons() throws SQLException;

	/**
	 *@param coupon
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To add new coupon or edit the existing coupon
	 */
	void addEditCoupon(Coupon coupon, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 *@param couponId
	 *@return Coupon
	 *@throws SQLException
	 *To fetch the details of the particular coupon
	 */
	Coupon fetchCoupon(int couponId) throws SQLException;

	/**
	 *@param couponId
	 *@throws SQLException
	 *To delete the particular coupon available
	 */
	void deleteCoupon(int couponId) throws SQLException;

}