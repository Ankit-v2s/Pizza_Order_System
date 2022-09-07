package com.pizzaordersystem.controller;

import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.model.Coupon;
import com.pizzaordersystem.model.Employee;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.PizzaMenu;
import com.pizzaordersystem.service.EmployeeService;
import com.pizzaordersystem.service.PizzaService;

/**
 * @author Ankit Madhavi
 *
 */
@RestController
public class EmployeeController {

	private static final String CREDENTIALS = "credentials";

	private static final String CUSTOMERLIST = "customerlist";

	private static final String PIZZALIST = "pizzalist";

	private static final String PAYMENTLIST = "paymentlist";

	private static final String FULLORDERLIST = "fullorderlist";

	private static final String PAYMENTMODELIST = "paymentmodelist";

	private static final String ORDERSTATUSLIST = "orderstatuslist";

	private static final String ORDERLIST = "orderlist";

	private static final String FEEDBACKLIST = "feedbacklist";

	private static final String COUPONLIST = "couponlist";

	private static final String CITY_LIST = "cityList";

	private static final String EMPLOYEEHOME = "employeehome";

	@Autowired
	@Qualifier("pizzaServiceImplementation")
	private PizzaService pizzaService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Load the employee home page with Orders of today's date
	 * 
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/employeehome")
	public ModelAndView employeeDashboard(ModelAndView modelAndView,
			HttpSession session)
			throws SQLException, CredentialsNotValidException {
		LoginCredentials credentials =  (LoginCredentials) session.getAttribute(CREDENTIALS);
		employeeService.checker(credentials);
		modelAndView.addObject(ORDERLIST, employeeService.fetchOrders());
		modelAndView.setViewName(EMPLOYEEHOME);
		return modelAndView;
	}

	/**
	 * To show the Details about all the Pizza available
	 * 
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/pizza")
	public ModelAndView pizzaDetails(ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(PIZZALIST, employeeService.fetchPizzaMenu());
		modelAndView.setViewName(PIZZALIST);
		return modelAndView;
	}

	/**
	 * Fetch the details of a specific Pizza
	 * 
	 * @param id
	 * @param credentials
	 * @return PizzaMenu
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/pizza/{id}")
	public PizzaMenu editPizza(@PathVariable int id, @SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		return employeeService.fetchPizza(id);
	}

	/**
	 * Add/Edit Pizza
	 * 
	 * @param pizzaMenu
	 * @param modelAndView
	 * @param result
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/add/pizza")
	public ModelAndView addPizza(@Valid @RequestBody PizzaMenu pizzaMenu, ModelAndView modelAndView,
			BindingResult result, @SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeService.checker(credentials);
		employeeService.addEditPizza(pizzaMenu, result);
		modelAndView.addObject(PIZZALIST, employeeService.fetchPizzaMenu());
		modelAndView.setViewName(PIZZALIST);
		return modelAndView;
	}

	/**
	 * Delete the specific pizza
	 * 
	 * @param pizzaId
	 * @param credentials
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@DeleteMapping("/delete/pizza/{pizzaId}")
	public void deletePizza(@PathVariable int pizzaId, @SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		employeeService.deletePizza(pizzaId);
	}

	/**
	 * To show the details of all the customers
	 * 
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/customer")
	public ModelAndView customerDetails(ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(CUSTOMERLIST, employeeService.fetchCustomer());
		modelAndView.setViewName(CUSTOMERLIST);
		return modelAndView;
	}

	/**
	 * To show the details of the specific employee
	 * 
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/employeedetails")
	public ModelAndView employeeDetails(ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject("employee", employeeService.fetchEmployee(credentials));
		modelAndView.addObject(CITY_LIST, pizzaService.fetchCity());
		modelAndView.setViewName("employeedetails");
		return modelAndView;
	}

	/**
	 * Update the employee details
	 * 
	 * @param employee
	 * @param result
	 * @param employeeId
	 * @param credentials
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PutMapping("/employee/{employeeId}")
	public void updateEmployee(@Valid @RequestBody Employee employee, @PathVariable int employeeId,
			BindingResult result, @SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeService.checker(credentials);
		employeeService.updateEmployee(employee, employeeId, result);
	}

	/**
	 * To show the details of all the coupons available
	 * 
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/coupons")
	public ModelAndView couponDetails(ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(COUPONLIST, employeeService.fetchCoupons());
		modelAndView.setViewName(COUPONLIST);
		return modelAndView;
	}

	/**
	 * Fetch details of the specific coupon
	 * 
	 * @param id
	 * @param credentials
	 * @return Coupon
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/coupon/{id}")
	public Coupon editCoupon(@PathVariable int id, @SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		return employeeService.fetchCoupon(id);
	}

	/**
	 * Add/Edit the coupon
	 * 
	 * @param coupon
	 * @param result
	 * @param credentials
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/add/coupon")
	public void addCoupon(@Valid @RequestBody Coupon coupon, BindingResult result,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeService.checker(credentials);
		employeeService.addEditCoupon(coupon, result);
	}

	/**
	 * Delete the specific coupon
	 * 
	 * @param couponId
	 * @param credentials
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@DeleteMapping("/delete/coupon/{couponId}")
	public void deleteCoupon(@PathVariable int couponId, @SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		employeeService.deleteCoupon(couponId);
	}

	/**
	 * To show the payment list
	 * 
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/payments")
	public ModelAndView paymentDetails(ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(PAYMENTMODELIST, employeeService.fetchPaymentModes());
		modelAndView.addObject(PAYMENTLIST, employeeService.fetchPayments());
		modelAndView.setViewName(PAYMENTLIST);
		return modelAndView;
	}

	/**
	 * To fetch the payment according to the mode selected
	 * 
	 * @param paymentMode
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/payment/{paymentMode}")
	public ModelAndView filterPayment(@PathVariable String paymentMode, ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(PAYMENTMODELIST, employeeService.fetchPaymentModes());
		modelAndView.addObject(PAYMENTLIST, employeeService.fetchPaymentByMode(paymentMode));
		modelAndView.setViewName(PAYMENTLIST);
		return modelAndView;
	}

	/**
	 * To display all the orders available
	 * 
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/orders")
	public ModelAndView orderDetails(ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(ORDERSTATUSLIST, employeeService.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, employeeService.fetchAllOrders());
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	/**
	 * To display all orders according to order status
	 * 
	 * @param statusType
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/order/{statusType}")
	public ModelAndView filterOrderByType(@PathVariable String statusType, ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(ORDERSTATUSLIST, employeeService.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, employeeService.fetchOrdersByStatusType(statusType));
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	/**
	 * To display all orders according to order date
	 * 
	 * @param date
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/order/date/{date}")
	public ModelAndView filterOrderByDate(@PathVariable Date date, ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(ORDERSTATUSLIST, employeeService.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, employeeService.fetchOrdersByDate(date));
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	/**
	 * To display all the feedbacks
	 * 
	 * @param modelAndView
	 * @param credentials
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/feedbacks")
	public ModelAndView feedbackDetails(ModelAndView modelAndView,
			@SessionAttribute(CREDENTIALS) LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker(credentials);
		modelAndView.addObject(FEEDBACKLIST, employeeService.fetchFeedback());
		modelAndView.setViewName(FEEDBACKLIST);
		return modelAndView;
	}

}
