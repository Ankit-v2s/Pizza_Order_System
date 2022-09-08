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

/**
 * @author Ankit Madhavi
 *
 */
public interface EmployeeDao {

	/**
	 *@param orderList
	 *@return List
	 *@throws SQLException
	 *To get all the orders according to the today's date
	 */
	List<Order> getOrders(List<Order> orderList) throws SQLException;

	/**
	 *@param orderList
	 *@return List
	 *@throws SQLException
	 *To get all the orders
	 */
	List<Order> getAllOrders(List<Order> orderList) throws SQLException;

	/**
	 *@param orderStatusList
	 *@return List
	 *@throws SQLException
	 *To get all the order status available
	 */
	List<OrderStatus> getOrderStatus(List<OrderStatus> orderStatusList) throws SQLException;

	/**
	 *@param orderList
	 *@param statusType
	 *@return List
	 *@throws SQLException
	 *To get all the orders according to the status type
	 */
	List<Order> getOrdersByStatusType(List<Order> orderList, String statusType) throws SQLException;

	/**
	 *@param orderList
	 *@param date
	 *@return List
	 *@throws SQLException
	 *To get all the orders according to the date selected
	 */
	List<Order> getOrdersByDate(List<Order> orderList, Date date) throws SQLException;

	/**
	 *@param customerDataList
	 *@return List
	 *@throws SQLException
	 *To get all the customers details available
	 */
	List<CustomerData> getCustomerData(List<CustomerData> customerDataList) throws SQLException;

	/**
	 *@param feedbackList
	 *@return List
	 *@throws SQLException
	 *To get all the feedback details available
	 */
	List<Feedback> getFeedback(List<Feedback> feedbackList) throws SQLException;

	/**
	 *@param pizzaList
	 *@return List
	 *@throws SQLException
	 *To get all the pizza details available
	 */
	List<PizzaMenu> getPizza(List<PizzaMenu> pizzaList) throws SQLException;

	/**
	 *@param pizzaMenu
	 *@throws SQLException
	 *To insert new pizza to the table
	 */
	void addPizza(PizzaMenu pizzaMenu) throws SQLException;

	/**
	 *@param pizzaId
	 *@return PizzaMenu
	 *@throws SQLException
	 *To get the pizza details according to the selected pizza
	 */
	PizzaMenu getPizza(int pizzaId) throws SQLException;

	/**
	 *@param pizzaMenu
	 *@throws SQLException
	 *To update the pizza details of particular pizza
	 */
	void updatePizza(PizzaMenu pizzaMenu) throws SQLException;

	/**
	 *@param pizzaId
	 *@throws SQLException
	 *To delete the selected pizza
	 */
	void deletePizza(int pizzaId) throws SQLException;

	/**
	 *@param loginCredentials
	 *@return Employee
	 *@throws SQLException
	 *To get the details of specific employee
	 */
	Employee getEmployee(LoginCredentials loginCredentials) throws SQLException;

	/**
	 *@param couponList
	 *@return List
	 *@throws SQLException
	 *To get all the coupons available
	 */
	List<Coupon> getCoupons(List<Coupon> couponList) throws SQLException;

	/**
	 *@param coupon
	 *@throws SQLException
	 *To insert new coupon to table
	 */
	void addCoupon(Coupon coupon) throws SQLException;

	/**
	 *@param couponId
	 *@return Coupon
	 *@throws SQLException
	 *To get the coupon details of the selected coupon
	 */
	Coupon getcoupon(int couponId) throws SQLException;

	/**
	 *@param coupon
	 *@throws SQLException
	 *To update the specific coupon details
	 */
	void updateCoupon(Coupon coupon) throws SQLException;

	/**
	 *@param couponId
	 *@throws SQLException
	 *To delete the specific coupon
	 */
	void deleteCoupon(int couponId) throws SQLException;

	/**
	 *@param paymentList
	 *@return List
	 *@throws SQLException
	 *To get all the payments available in the table
	 */
	List<Payment> getPayments(List<Payment> paymentList) throws SQLException;

	/**
	 *@param paymentModeList
	 *@return List
	 *@throws SQLException
	 *To get all the payments modes available in the table
	 */
	List<PaymentModes> getPaymentModes(List<PaymentModes> paymentModeList) throws SQLException;

	/**
	 *@param paymentList
	 *@param paymentMode
	 *@return List
	 *@throws SQLException
	 *To get all the payments according to payment mode 
	 */
	List<Payment> getPaymentByMode(List<Payment> paymentList, String paymentMode) throws SQLException;

	/**
	 *@param employee
	 *@param employeeId
	 *@throws SQLException
	 *To update the details for the employee
	 */
	void updateEmployee(Employee employee, int employeeId) throws SQLException;

}