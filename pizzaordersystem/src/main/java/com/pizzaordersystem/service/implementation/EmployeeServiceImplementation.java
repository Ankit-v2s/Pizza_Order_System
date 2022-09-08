package com.pizzaordersystem.service.implementation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.EmployeeDao;
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
import com.pizzaordersystem.service.EmployeeService;

/**
 * @author Ankit Madhavi
 *
 */
@Service
public class EmployeeServiceImplementation implements EmployeeService {

	private static final String NO_ACCESS_TO_THIS_PAGE = "No Access to this Page";
	private static final String ADMIN = "admin";
	@Autowired
	private EmployeeDao employeeDao;

	/**
	 * To check if only admin has access
	 * 
	 * @param loginCredentials
	 * @throws CredentialsNotValidException
	 */
	@Override
	public void checker(LoginCredentials loginCredentials) throws CredentialsNotValidException {
		if (!loginCredentials.getRoles().equals(ADMIN)) {
			throw new CredentialsNotValidException(NO_ACCESS_TO_THIS_PAGE);
		}
	}

	/**
	 * To fetch the list of orders for the present day;
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<Order> fetchOrders() throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return employeeDao.getOrders(orderList);
	}

	/**
	 * To fetch list of all the available orders
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<Order> fetchAllOrders() throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return employeeDao.getAllOrders(orderList);
	}

	/**
	 * To fetch list of order status available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<OrderStatus> fetchOrderStatus() throws SQLException {
		List<OrderStatus> orderStatusList = new ArrayList<>();
		return employeeDao.getOrderStatus(orderStatusList);
	}

	/**
	 * To fetch the list of orders according to the order status selected
	 * 
	 * @param statusType
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<Order> fetchOrdersByStatusType(String statusType) throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return employeeDao.getOrdersByStatusType(orderList, statusType);
	}

	/**
	 * To fetch the list of orders according to the date selected
	 * 
	 * @param date
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<Order> fetchOrdersByDate(Date date) throws SQLException {
		List<Order> orderList = new ArrayList<>();
		return employeeDao.getOrdersByDate(orderList, date);
	}

	/**
	 * To fetch the list of all the feedbacks available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<Feedback> fetchFeedback() throws SQLException {
		List<Feedback> feedbackList = new ArrayList<>();
		return employeeDao.getFeedback(feedbackList);
	}

	/**
	 * To fetch the list of all the customers available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<CustomerData> fetchCustomer() throws SQLException {
		List<CustomerData> customerDataList = new ArrayList<>();
		return employeeDao.getCustomerData(customerDataList);
	}

	/**
	 * To fetch the data of the logged in employee
	 * 
	 * @param credentials
	 * @return Employee
	 * @throws SQLException
	 */
	@Override
	public Employee fetchEmployee(LoginCredentials credentials) throws SQLException {
		return employeeDao.getEmployee(credentials);
	}

	/**
	 * To update the details of the employee
	 * 
	 * @param employee
	 * @param employeeId
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	@Override
	public void updateEmployee(Employee employee, int employeeId, BindingResult result)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			employeeDao.updateEmployee(employee, employeeId);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 * To fetch the list of all the payments available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<Payment> fetchPayments() throws SQLException {
		List<Payment> paymentList = new ArrayList<>();
		return employeeDao.getPayments(paymentList);
	}

	/**
	 * To fetch the list of all the payment modes available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<PaymentModes> fetchPaymentModes() throws SQLException {
		List<PaymentModes> paymentModeList = new ArrayList<>();
		return employeeDao.getPaymentModes(paymentModeList);
	}

	/**
	 * To fetch the list of payments according to payment modes
	 * 
	 * @param paymentMode
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<Payment> fetchPaymentByMode(String paymentMode) throws SQLException {
		List<Payment> paymentList = new ArrayList<>();
		return employeeDao.getPaymentByMode(paymentList, paymentMode);
	}

	/**
	 * To fetch all the list of pizza available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<PizzaMenu> fetchPizzaMenu() throws SQLException {
		List<PizzaMenu> pizzaList = new ArrayList<>();
		return employeeDao.getPizza(pizzaList);
	}

	/**
	 * To add new pizza or edit the available pizza
	 * 
	 * @param pizzaMenu
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	@Override
	public void addEditPizza(PizzaMenu pizzaMenu, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			checkAddOrUpdatePizza(pizzaMenu);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 * To check if Pizza is to be added or updated
	 * 
	 * @param pizzaMenu
	 * @throws SQLException
	 */
	private void checkAddOrUpdatePizza(PizzaMenu pizzaMenu) throws SQLException {
		if (pizzaMenu.getPizzaId() == 0) {
			employeeDao.addPizza(pizzaMenu);
		} else {
			employeeDao.updatePizza(pizzaMenu);
		}
	}

	/**
	 * To fetch the details of the particular pizza
	 * 
	 * @param pizzaId
	 * @return PizzaMenu
	 * @throws SQLException
	 */
	@Override
	public PizzaMenu fetchPizza(int pizzaId) throws SQLException {
		return employeeDao.getPizza(pizzaId);
	}

	/**
	 * To delete the particular pizza
	 * 
	 * @param pizzaId
	 * @throws SQLException
	 */
	@Override
	public void deletePizza(int pizzaId) throws SQLException {
		employeeDao.deletePizza(pizzaId);
	}

	/**
	 * To fetch the list of all the coupons available
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<Coupon> fetchCoupons() throws SQLException {
		List<Coupon> couponList = new ArrayList<>();
		return employeeDao.getCoupons(couponList);
	}

	/**
	 * To add new coupon or edit the existing coupon
	 * 
	 * @param coupon
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	@Override
	public void addEditCoupon(Coupon coupon, BindingResult result) throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			checkAddOrUpdateCoupon(coupon);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 * Check if to add or update the coupon
	 * 
	 * @param coupon
	 * @throws SQLException
	 */
	private void checkAddOrUpdateCoupon(Coupon coupon) throws SQLException {
		if (coupon.getCouponId() == 0) {
			employeeDao.addCoupon(coupon);
		} else {
			employeeDao.updateCoupon(coupon);
		}
	}

	/**
	 * To fetch the details of the particular coupon
	 * 
	 * @param couponId
	 * @return Coupon
	 * @throws SQLException
	 */
	@Override
	public Coupon fetchCoupon(int couponId) throws SQLException {
		return employeeDao.getcoupon(couponId);
	}

	/**
	 * To delete the particular coupon available
	 * 
	 * @param couponId
	 * @throws SQLException
	 */
	@Override
	public void deleteCoupon(int couponId) throws SQLException {
		employeeDao.deleteCoupon(couponId);
	}
}
