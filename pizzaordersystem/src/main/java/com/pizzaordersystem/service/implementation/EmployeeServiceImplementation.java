package com.pizzaordersystem.service.implementation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.PizzaDao;
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
import com.pizzaordersystem.service.EmployeeService;

public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private PizzaDao pizzaDaoImplementation;
	@Autowired
	private PizzaServiceImplementation pizzaServiceImplementation;
	
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
		return pizzaDaoImplementation.getEmployee(pizzaServiceImplementation.getLoginCredentials());
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
}
