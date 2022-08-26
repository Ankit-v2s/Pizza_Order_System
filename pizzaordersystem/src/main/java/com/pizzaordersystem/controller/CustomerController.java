package com.pizzaordersystem.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.exception.ZeroAmountException;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.service.PizzaService;

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
	private PizzaService pizzaServiceImplementation;

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException. 
	 * Load Customer Homepage
	 */
	@GetMapping("/customerhome")
	public ModelAndView customerDashboard(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(PIZZALIST, pizzaServiceImplementation.fetchPizzaMenu());
		modelAndView.addObject(PAYMENTMODELIST, pizzaServiceImplementation.fetchPaymentModes());
		modelAndView.addObject(COUPONLIST, pizzaServiceImplementation.fetchCoupons());
		modelAndView.addObject("cartList", pizzaServiceImplementation.getCartList());
		modelAndView.setViewName("customerhome");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * Fetch the details of the specific customer who is logged in
	 */
	@GetMapping("/editcustomer")
	public ModelAndView customer(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject("customer", pizzaServiceImplementation.fetchCustomerDetails());
		modelAndView.addObject(CITY_LIST, pizzaServiceImplementation.fetchCity());
		modelAndView.setViewName("customerdetails");
		return modelAndView;
	}

	/**
	 * @param customerData
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * Update the customer details
	 */
	@PutMapping("/customer/{customerId}")
	public void updateCustomer(@Valid @RequestBody CustomerData customerData, int customerId, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.updateCustomer(customerData, customerId, result);
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * Load feedback page
	 */
	@GetMapping("/feedback")
	public ModelAndView addFeedback(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(FEEDBACKLIST, pizzaServiceImplementation.fetchFeedbackStatus());
		modelAndView.setViewName("feedback");
		return modelAndView;
	}

	/**
	 * @param feedback
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * Add feedback
	 */
	@PostMapping("/add/feedback")
	public void addFeedback(@Valid @RequestBody Feedback feedback, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.addFeedback(feedback, result);
	}

	/**
	 * @param pizza
	 * @param result
	 * @return List<PizzaOrder>
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * Load the cart table according to the pizza added
	 */
	@PostMapping("/add/item")
	public List<PizzaOrder> orderPizza(@Valid @RequestBody PizzaOrder pizza, BindingResult result)
			throws SQLException, InvalidFieldException {
		return pizzaServiceImplementation.addItem(pizza, result);
	}

	/**
	 * @return int
	 * @throws SQLException
	 * Add the order
	 */
	@GetMapping("/order/pizza")
	public int orderDetails() throws SQLException {
		pizzaServiceImplementation.addOrder();
		return pizzaServiceImplementation.orderPizza();
	}

	/**
	 * @param pizzaOrder
	 * @return int
	 * @throws SQLException
	 * @throws ZeroAmountException
	 * Apply discount according to the coupon
	 */
	@PostMapping("/order/pizza/discount")
	public int discountPrice(@RequestBody PizzaOrder pizzaOrder) throws SQLException, ZeroAmountException {
		return pizzaServiceImplementation.discountPrice(pizzaOrder);
	}

	/**
	 * @param payment
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * Add payment
	 */
	@PostMapping("/pay/order")
	public void addPayment(@Valid @RequestBody Payment payment, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.addPayment(payment, result);
	}
}
