package com.pizzaordersystem.controller;

import java.sql.Date;
import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaordersystem.exception.CredentialCheckerException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.Coupon;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Employee;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaMenu;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.model.RegisterDetails;
import com.pizzaordersystem.service.PizzaService;

@RestController
public class PizzaController {

	private static final String CITY_LIST = "cityList";

	private static final String PAYMENTMODELIST = "paymentmodelist";

	private static final String ORDERSTATUSLIST = "orderstatuslist";

	private static final String ORDERLIST = "orderlist";

	private static final String FEEDBACKLIST = "feedbacklist";

	private static final String COUPONLIST = "couponlist";

	private static final String CUSTOMERLIST = "customerlist";

	private static final String PIZZALIST = "pizzalist";

	private static final String PAYMENTLIST = "paymentlist";

	private static final String FULLORDERLIST = "fullorderlist";

	private static final String EMPLOYEEHOME = "employeehome";

	private static final String LOGIN = "login";

	@Autowired
	private PizzaService pizzaServiceImplementation;

	String view = null;

	@RequestMapping("/")
	public ModelAndView loginPage(ModelAndView modelAndView) {
		pizzaServiceImplementation.logout();
		modelAndView.setViewName(LOGIN);
		return modelAndView;
	}

	@PostMapping("/login")
	public String checkLogin(@Valid @RequestBody LoginCredentials loginCredentials, BindingResult result, Model model)
			throws ClassNotFoundException, SQLException, CredentialCheckerException, InvalidFieldException {
		pizzaServiceImplementation.createConnection();
		return pizzaServiceImplementation.credentialChecker(loginCredentials, result, model);
	}

	@GetMapping("/city/{city}")
	public City loadCityDetails(@PathVariable String city) throws SQLException {
		return pizzaServiceImplementation.fetchCityDetails(city);
	}

	@GetMapping("/employeehome")
	public ModelAndView employeeDashboard(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(ORDERLIST, pizzaServiceImplementation.fetchOrders());
		modelAndView.setViewName(EMPLOYEEHOME);
		return modelAndView;
	}

	@GetMapping("/customerhome")
	public ModelAndView customerDashboard(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(PIZZALIST, pizzaServiceImplementation.fetchPizzaMenu());
		modelAndView.addObject(PAYMENTMODELIST, pizzaServiceImplementation.fetchPaymentModes());
		modelAndView.addObject(COUPONLIST, pizzaServiceImplementation.fetchCoupons());
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

	@GetMapping("/pizza")
	public ModelAndView pizzaDetails(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(PIZZALIST, pizzaServiceImplementation.fetchPizzaMenu());
		modelAndView.setViewName(PIZZALIST);
		return modelAndView;
	}

	@GetMapping("/customer")
	public ModelAndView customerDetails(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(CUSTOMERLIST, pizzaServiceImplementation.fetchCustomer());
		modelAndView.setViewName(CUSTOMERLIST);
		return modelAndView;
	}

	@GetMapping("/employeedetails")
	public ModelAndView employeeDetails(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject("employee", pizzaServiceImplementation.fetchEmployee());
		modelAndView.addObject(CITY_LIST, pizzaServiceImplementation.fetchCity());
		modelAndView.setViewName("employeedetails");
		return modelAndView;
	}

	@GetMapping("/coupons")
	public ModelAndView couponDetails(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(COUPONLIST, pizzaServiceImplementation.fetchCoupons());
		modelAndView.setViewName(COUPONLIST);
		return modelAndView;
	}

	@GetMapping("/payments")
	public ModelAndView paymentDetails(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(PAYMENTMODELIST, pizzaServiceImplementation.fetchPaymentModes());
		modelAndView.addObject(PAYMENTLIST, pizzaServiceImplementation.fetchPayments());
		modelAndView.setViewName(PAYMENTLIST);
		return modelAndView;
	}

	@GetMapping("/orders")
	public ModelAndView orderDetails(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(ORDERSTATUSLIST, pizzaServiceImplementation.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, pizzaServiceImplementation.fetchAllOrders());
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	@GetMapping("/feedbacks")
	public ModelAndView feedbackDetails(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(FEEDBACKLIST, pizzaServiceImplementation.fetchFeedback());
		modelAndView.setViewName(FEEDBACKLIST);
		return modelAndView;
	}

	@GetMapping("/signup")
	public ModelAndView signUp(ModelAndView modelAndView) throws ClassNotFoundException, SQLException {
		pizzaServiceImplementation.createConnection();
		modelAndView.addObject(CITY_LIST, pizzaServiceImplementation.fetchCity());
		modelAndView.setViewName("signup");
		return modelAndView;
	}

	@GetMapping("/pizza/{id}")
	public PizzaMenu editPizza(@PathVariable int id) throws SQLException {
		return pizzaServiceImplementation.fetchPizza(id);
	}

	@PostMapping("/add/pizza")
	public ModelAndView addPizza(@RequestBody PizzaMenu pizzaMenu, ModelAndView modelAndView)
			throws SQLException {
		pizzaServiceImplementation.addEditPizza(pizzaMenu);
		modelAndView.addObject(PIZZALIST, pizzaServiceImplementation.fetchPizzaMenu());
		modelAndView.setViewName(PIZZALIST);
		return modelAndView;
	}

	@PostMapping("/add/customer")
	public void addCustomer(@Valid @RequestBody RegisterDetails details, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.addCustomer(details, result);
	}

	@DeleteMapping("/delete/pizza/{pizzaId}")
	public void deletePizza(@PathVariable int pizzaId) throws SQLException {
		pizzaServiceImplementation.deletePizza(pizzaId);
	}

	@GetMapping("/coupon/{id}")
	public Coupon editCoupon(@PathVariable int id) throws SQLException {
		return pizzaServiceImplementation.fetchCoupon(id);
	}

	@PostMapping("/add/coupon")
	public void addCoupon(@RequestBody Coupon coupon) throws SQLException {
		pizzaServiceImplementation.addEditCoupon(coupon);
	}

	@DeleteMapping("/delete/coupon/{couponId}")
	public void deleteCoupon(@PathVariable int couponId) throws SQLException {
		pizzaServiceImplementation.deleteCoupon(couponId);
	}

	@GetMapping("/order/{statusType}")
	public ModelAndView filterOrderByType(@PathVariable String statusType, ModelAndView modelAndView)
			throws SQLException {
		modelAndView.addObject(ORDERSTATUSLIST, pizzaServiceImplementation.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, pizzaServiceImplementation.fetchOrdersByStatusType(statusType));
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	@GetMapping("/order/date/{date}")
	public ModelAndView filterOrderByDate(@PathVariable Date date, ModelAndView modelAndView)
			throws SQLException {
		modelAndView.addObject(ORDERSTATUSLIST, pizzaServiceImplementation.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, pizzaServiceImplementation.fetchOrdersByDate(date));
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	@GetMapping("/payment/{paymentMode}")
	public ModelAndView filterPayment(@PathVariable String paymentMode, ModelAndView modelAndView)
			throws SQLException {
		modelAndView.addObject(PAYMENTMODELIST, pizzaServiceImplementation.fetchPaymentModes());
		modelAndView.addObject(PAYMENTLIST, pizzaServiceImplementation.fetchPaymentByMode(paymentMode));
		modelAndView.setViewName(PAYMENTLIST);
		return modelAndView;
	}

	@PutMapping("/employee/update")
	public void updateEmployee(@Valid @RequestBody Employee employee,BindingResult result) throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.updateEmployee(employee,result);
	}

	@PutMapping("/customer/update")
	public void updateCustomer(@Valid @RequestBody CustomerData customerData,BindingResult result) throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.updateCustomer(customerData,result);
	}

	@PostMapping("/add/feedback")
	public void addFeedback(@RequestBody Feedback feedback) throws SQLException {
		pizzaServiceImplementation.addFeedback(feedback);
	}

	@PostMapping("/add/item")
	public void orderPizza(@Valid @RequestBody PizzaOrder pizza, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.addItem(pizza, result);
	}

	@GetMapping("/order/pizza")
	public int orderDetails() throws SQLException {
		return pizzaServiceImplementation.orderPizza();
	}

	@PostMapping("/order/pizza/discount")
	public int discountPrice(@RequestBody PizzaOrder pizzaOrder) throws SQLException {
		return pizzaServiceImplementation.discountPrice(pizzaOrder);
	}

	@PostMapping("/pay/order")
	public void addPayment(@Valid @RequestBody Payment payment, BindingResult result) throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.addOrder(result);
		pizzaServiceImplementation.addPayment(payment,result);
	}
}
