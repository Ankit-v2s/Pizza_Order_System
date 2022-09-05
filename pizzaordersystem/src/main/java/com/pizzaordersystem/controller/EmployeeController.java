package com.pizzaordersystem.controller;

import java.sql.Date;
import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.model.Coupon;
import com.pizzaordersystem.model.Employee;
import com.pizzaordersystem.model.PizzaMenu;
import com.pizzaordersystem.service.EmployeeService;
import com.pizzaordersystem.service.PizzaService;

/**
 * @author Ankit Madhavi
 *
 */
@RestController
public class EmployeeController {

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
	private PizzaService pizzaService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Load the employee home page with Orders of today's date
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/employeehome")
	public ModelAndView employeeDashboard(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		modelAndView.addObject(ORDERLIST, employeeService.fetchOrders());
		modelAndView.setViewName(EMPLOYEEHOME);
		return modelAndView;
	}

	/**
	 * To show the Details about all the Pizza available
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/pizza")
	public ModelAndView pizzaDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		modelAndView.addObject(PIZZALIST, employeeService.fetchPizzaMenu());
		modelAndView.setViewName(PIZZALIST);
		return modelAndView;
	}

	/**
	 * Fetch the details of a specific Pizza
	 * 
	 * @param id
	 * @return PizzaMenu
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/pizza/{id}")
	public PizzaMenu editPizza(@PathVariable int id) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		return employeeService.fetchPizza(id);
	}

	/**
	 * Add/Edit Pizza
	 * 
	 * @param pizzaMenu
	 * @param modelAndView
	 * @param result
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/add/pizza")
	public ModelAndView addPizza(@Valid @RequestBody PizzaMenu pizzaMenu, ModelAndView modelAndView,
			BindingResult result) throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeService.checker();
		employeeService.addEditPizza(pizzaMenu, result);
		modelAndView.addObject(PIZZALIST, employeeService.fetchPizzaMenu());
		modelAndView.setViewName(PIZZALIST);
		return modelAndView;
	}

	/**
	 * Delete the specific pizza
	 * 
	 * @param pizzaId
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@DeleteMapping("/delete/pizza/{pizzaId}")
	public void deletePizza(@PathVariable int pizzaId) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		employeeService.deletePizza(pizzaId);
	}

	/**
	 * To show the details of all the customers
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/customer")
	public ModelAndView customerDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		modelAndView.addObject(CUSTOMERLIST, employeeService.fetchCustomer());
		modelAndView.setViewName(CUSTOMERLIST);
		return modelAndView;
	}

	/**
	 * To show the details of the specific employee
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/employeedetails")
	public ModelAndView employeeDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		modelAndView.addObject("employee", employeeService.fetchEmployee());
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
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PutMapping("/employee/{employeeId}")
	public void updateEmployee(@Valid @RequestBody Employee employee, @PathVariable int employeeId,
			BindingResult result) throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeService.checker();
		employeeService.updateEmployee(employee, employeeId, result);
	}

	/**
	 * To show the details of all the coupons available
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/coupons")
	public ModelAndView couponDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		modelAndView.addObject(COUPONLIST, employeeService.fetchCoupons());
		modelAndView.setViewName(COUPONLIST);
		return modelAndView;
	}

	/**
	 * Fetch details of the specific coupon
	 * 
	 * @param id
	 * @return Coupon
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/coupon/{id}")
	public Coupon editCoupon(@PathVariable int id) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		return employeeService.fetchCoupon(id);
	}

	/**
	 * Add/Edit the coupon
	 * 
	 * @param coupon
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/add/coupon")
	public void addCoupon(@Valid @RequestBody Coupon coupon, BindingResult result)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeService.checker();
		employeeService.addEditCoupon(coupon, result);
	}

	/**
	 * Delete the specific coupon
	 * 
	 * @param couponId
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@DeleteMapping("/delete/coupon/{couponId}")
	public void deleteCoupon(@PathVariable int couponId) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		employeeService.deleteCoupon(couponId);
	}

	/**
	 * To show the payment list
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/payments")
	public ModelAndView paymentDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
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
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/payment/{paymentMode}")
	public ModelAndView filterPayment(@PathVariable String paymentMode, ModelAndView modelAndView)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		modelAndView.addObject(PAYMENTMODELIST, employeeService.fetchPaymentModes());
		modelAndView.addObject(PAYMENTLIST, employeeService.fetchPaymentByMode(paymentMode));
		modelAndView.setViewName(PAYMENTLIST);
		return modelAndView;
	}

	/**
	 * To display all the orders available
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/orders")
	public ModelAndView orderDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
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
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/order/{statusType}")
	public ModelAndView filterOrderByType(@PathVariable String statusType, ModelAndView modelAndView)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker();
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
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/order/date/{date}")
	public ModelAndView filterOrderByDate(@PathVariable Date date, ModelAndView modelAndView)
			throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		modelAndView.addObject(ORDERSTATUSLIST, employeeService.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, employeeService.fetchOrdersByDate(date));
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	/**
	 * To display all the feedbacks
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws CredentialsNotValidException
	 */
	@GetMapping("/feedbacks")
	public ModelAndView feedbackDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeService.checker();
		modelAndView.addObject(FEEDBACKLIST, employeeService.fetchFeedback());
		modelAndView.setViewName(FEEDBACKLIST);
		return modelAndView;
	}

}
