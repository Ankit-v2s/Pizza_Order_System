package com.pizzaordersystem.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
import com.pizzaordersystem.exception.ZeroAmountException;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.service.CustomerService;
import com.pizzaordersystem.service.EmployeeService;
import com.pizzaordersystem.service.implementation.PizzaServiceImplementation;

/**
 * @author Ankit Madhavi
 *
 */
@RestController
public class CustomerController {

	private static final String PAYMENTMODELIST = "paymentmodelist";

	private static final String FEEDBACKLIST = "feedbacklist";

	private static final String COUPONLIST = "couponlist";

	private static final String PIZZALIST = "pizzalist";

	private static final String CITY_LIST = "cityList";

	@Autowired
	private PizzaServiceImplementation pizzaServiceImplementation;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Load Customer Homepage
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws CredentialsNotValidException
	 * @throws SQLException.
	 */
	@GetMapping("/customerhome")
	public ModelAndView customerDashboard(ModelAndView modelAndView,
			@SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		customerService.checker(credentials);
		modelAndView.addObject(PIZZALIST, employeeService.fetchPizzaMenu());
		modelAndView.addObject(PAYMENTMODELIST, employeeService.fetchPaymentModes());
		modelAndView.addObject(COUPONLIST, employeeService.fetchCoupons());
		modelAndView.addObject("cartList", pizzaServiceImplementation.getCartList());
		modelAndView.setViewName("customerhome");
		return modelAndView;
	}

	/**
	 * Fetch the details of the specific customer who is logged in
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/editcustomer")
	public ModelAndView customer(ModelAndView modelAndView,
			@SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		customerService.checker(credentials);
		modelAndView.addObject("customer", customerService.fetchCustomerDetails(credentials));
		modelAndView.addObject(CITY_LIST, pizzaServiceImplementation.fetchCity());
		modelAndView.setViewName("customerdetails");
		return modelAndView;
	}

	/**
	 * Update the customer details
	 * 
	 * @param customerData
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PutMapping("/customer/{customerId}")
	public void updateCustomer(@Valid @RequestBody CustomerData customerData, @PathVariable int customerId,
			BindingResult result, @SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		customerService.checker(credentials);
		customerService.updateCustomer(customerData, customerId, result);
	}

	/**
	 * Load feedback page
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/feedback")
	public ModelAndView addFeedback(ModelAndView modelAndView,
			@SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		customerService.checker(credentials);
		modelAndView.addObject(FEEDBACKLIST, customerService.fetchFeedbackStatus());
		modelAndView.setViewName("feedback");
		return modelAndView;
	}

	/**
	 * Add feedback
	 * 
	 * @param feedback
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/add/feedback")
	public void addFeedback(@Valid @RequestBody Feedback feedback, BindingResult result,
			@SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		customerService.checker(credentials);
		customerService.addFeedback(feedback, result, credentials);
	}

	/**
	 * Load the cart table according to the pizza added
	 * 
	 * @param pizza
	 * @param result
	 * @return List<PizzaOrder>
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/add/item")
	public List<PizzaOrder> orderPizza(@Valid @RequestBody PizzaOrder pizza, BindingResult result,
			@SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		customerService.checker(credentials);
		return customerService.addItem(pizza, result);
	}

	/**
	 * Add the order
	 * 
	 * @return int
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/order/pizza")
	public int orderDetails(@SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, CredentialsNotValidException {
		customerService.checker(credentials);
		customerService.addOrder(credentials);
		return customerService.calculate();
	}

	/**
	 * Apply discount according to the coupon
	 * 
	 * @param pizzaOrder
	 * @return discount
	 * @throws SQLException
	 * @throws ZeroAmountException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/order/pizza/discount")
	public int discountPrice(@RequestBody PizzaOrder pizzaOrder,
			@SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, ZeroAmountException, CredentialsNotValidException {
		customerService.checker(credentials);
		return customerService.discountPrice(pizzaOrder);
	}

	/**
	 * Add payment
	 * 
	 * @param payment
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/pay/order")
	public void addPayment(@Valid @RequestBody Payment payment, BindingResult result,
			@SessionAttribute("credentials") LoginCredentials credentials)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		customerService.checker(credentials);
		customerService.addPayment(payment, result, credentials);
	}
}
