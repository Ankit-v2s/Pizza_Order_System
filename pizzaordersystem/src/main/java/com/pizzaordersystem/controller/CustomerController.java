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
	
	@GetMapping("/customerhome")
	public ModelAndView customerDashboard(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(PIZZALIST, pizzaServiceImplementation.fetchPizzaMenu());
		modelAndView.addObject(PAYMENTMODELIST, pizzaServiceImplementation.fetchPaymentModes());
		modelAndView.addObject(COUPONLIST, pizzaServiceImplementation.fetchCoupons());
		modelAndView.addObject("cartList",pizzaServiceImplementation.getCartList());
		modelAndView.setViewName("customerhome");
		return modelAndView;
	}

	@GetMapping("/editcustomer")
	public ModelAndView customer(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject("customer", pizzaServiceImplementation.fetchCustomerDetails());
		modelAndView.addObject(CITY_LIST, pizzaServiceImplementation.fetchCity());
		modelAndView.setViewName("customerdetails");
		return modelAndView;
	}

	@GetMapping("/feedback")
	public ModelAndView addFeedback(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(FEEDBACKLIST, pizzaServiceImplementation.fetchFeedbackStatus());
		modelAndView.setViewName("feedback");
		return modelAndView;
	}
	
	@PutMapping("/customer/update")
	public void updateCustomer(@Valid @RequestBody CustomerData customerData, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.updateCustomer(customerData, result);
	}

	@PostMapping("/add/feedback")
	public void addFeedback(@Valid @RequestBody Feedback feedback, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.addFeedback(feedback, result);
	}

	@PostMapping("/add/item")
	public List<PizzaOrder> orderPizza(@Valid @RequestBody PizzaOrder pizza, BindingResult result)
			throws SQLException, InvalidFieldException {
		return pizzaServiceImplementation.addItem(pizza, result);
	}

	@GetMapping("/order/pizza")
	public int orderDetails() throws SQLException {
		pizzaServiceImplementation.addOrder();
		return pizzaServiceImplementation.orderPizza();
	}

	@PostMapping("/order/pizza/discount")
	public int discountPrice(@RequestBody PizzaOrder pizzaOrder) throws SQLException, ZeroAmountException {
		return pizzaServiceImplementation.discountPrice(pizzaOrder);
	}

	@PostMapping("/pay/order")
	public void addPayment(@Valid @RequestBody Payment payment, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.addPayment(payment, result);
	}
}
