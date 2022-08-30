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
	private PizzaService pizzaServiceImplementation;
	@Autowired
	private EmployeeService employeeServiceImplementation;

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException 
	 * Load the employee home page with Orders of today's date
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/employeehome")
	public ModelAndView employeeDashboard(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(ORDERLIST, employeeServiceImplementation.fetchOrders());
		modelAndView.setViewName(EMPLOYEEHOME);
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException 
	 * To show the Details about all the Pizza available
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/pizza")
	public ModelAndView pizzaDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(PIZZALIST, employeeServiceImplementation.fetchPizzaMenu());
		modelAndView.setViewName(PIZZALIST);
		return modelAndView;
	}
	
	/**
	 * @param id
	 * @return PizzaMenu
	 * @throws SQLException
	 * Fetch the details of a specific Pizza
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/pizza/{id}")
	public PizzaMenu editPizza(@PathVariable int id) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		return employeeServiceImplementation.fetchPizza(id);
	}

	/**
	 * @param pizzaMenu
	 * @param modelAndView
	 * @param result
	 * @return ModelAndView
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * Add/Edit Pizza 
	 * @throws CredentialsNotValidException 
	 */
	@PostMapping("/add/pizza")
	public ModelAndView addPizza(@Valid @RequestBody PizzaMenu pizzaMenu, ModelAndView modelAndView,
			BindingResult result) throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		employeeServiceImplementation.addEditPizza(pizzaMenu, result);
		modelAndView.addObject(PIZZALIST, employeeServiceImplementation.fetchPizzaMenu());
		modelAndView.setViewName(PIZZALIST);
		return modelAndView;
	}

	/**
	 * @param pizzaId
	 * @throws SQLException
	 * Delete the specific pizza
	 * @throws CredentialsNotValidException 
	 */
	@DeleteMapping("/delete/pizza/{pizzaId}")
	public void deletePizza(@PathVariable int pizzaId) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		employeeServiceImplementation.deletePizza(pizzaId);
	}


	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException 
	 * To show the details of all the customers
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/customer")
	public ModelAndView customerDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(CUSTOMERLIST, employeeServiceImplementation.fetchCustomer());
		modelAndView.setViewName(CUSTOMERLIST);
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException 
	 * To show the details of the specific employee
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/employeedetails")
	public ModelAndView employeeDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject("employee", employeeServiceImplementation.fetchEmployee());
		modelAndView.addObject(CITY_LIST, pizzaServiceImplementation.fetchCity());
		modelAndView.setViewName("employeedetails");
		return modelAndView;
	}

	/**
	 * @param employee
	 * @param result
	 * @param employeeId
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * Update the employee details
	 * @throws CredentialsNotValidException 
	 */
	@PutMapping("/employee/{employeeId}")
	public void updateEmployee(@Valid @RequestBody Employee employee, @PathVariable int employeeId, BindingResult result)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		employeeServiceImplementation.updateEmployee(employee,employeeId, result);
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException 
	 * To show the details of all the coupons available
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/coupons")
	public ModelAndView couponDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(COUPONLIST, employeeServiceImplementation.fetchCoupons());
		modelAndView.setViewName(COUPONLIST);
		return modelAndView;
	}
	
	/**
	 * @param id
	 * @return Coupon
	 * @throws SQLException
	 * Fetch details of the specific coupon
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/coupon/{id}")
	public Coupon editCoupon(@PathVariable int id) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		return employeeServiceImplementation.fetchCoupon(id);
	}

	/**
	 * @param coupon
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 * Add/Edit the coupon
	 * @throws CredentialsNotValidException 
	 */
	@PostMapping("/add/coupon")
	public void addCoupon(@Valid @RequestBody Coupon coupon, BindingResult result)
			throws SQLException, InvalidFieldException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		employeeServiceImplementation.addEditCoupon(coupon, result);
	}

	/**
	 * @param couponId
	 * @throws SQLException
	 * Delete the specific coupon
	 * @throws CredentialsNotValidException 
	 */
	@DeleteMapping("/delete/coupon/{couponId}")
	public void deleteCoupon(@PathVariable int couponId) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		employeeServiceImplementation.deleteCoupon(couponId);
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * To show the payment list
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/payments")
	public ModelAndView paymentDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(PAYMENTMODELIST, employeeServiceImplementation.fetchPaymentModes());
		modelAndView.addObject(PAYMENTLIST, employeeServiceImplementation.fetchPayments());
		modelAndView.setViewName(PAYMENTLIST);
		return modelAndView;
	}

	/**
	 * @param paymentMode
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * To fetch the payment according to the mode selected
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/payment/{paymentMode}")
	public ModelAndView filterPayment(@PathVariable String paymentMode, ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(PAYMENTMODELIST, employeeServiceImplementation.fetchPaymentModes());
		modelAndView.addObject(PAYMENTLIST, employeeServiceImplementation.fetchPaymentByMode(paymentMode));
		modelAndView.setViewName(PAYMENTLIST);
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * To display all the orders available
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/orders")
	public ModelAndView orderDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(ORDERSTATUSLIST, employeeServiceImplementation.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, employeeServiceImplementation.fetchAllOrders());
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	/**
	 * @param statusType
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * To display all orders according to order status
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/order/{statusType}")
	public ModelAndView filterOrderByType(@PathVariable String statusType, ModelAndView modelAndView)
			throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(ORDERSTATUSLIST, employeeServiceImplementation.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, employeeServiceImplementation.fetchOrdersByStatusType(statusType));
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	/**
	 * @param date
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * To display all orders according to order date
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/order/date/{date}")
	public ModelAndView filterOrderByDate(@PathVariable Date date, ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(ORDERSTATUSLIST, employeeServiceImplementation.fetchOrderStatus());
		modelAndView.addObject(FULLORDERLIST, employeeServiceImplementation.fetchOrdersByDate(date));
		modelAndView.setViewName(ORDERLIST);
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 * To display all the feedbacks
	 * @throws CredentialsNotValidException 
	 */
	@GetMapping("/feedbacks")
	public ModelAndView feedbackDetails(ModelAndView modelAndView) throws SQLException, CredentialsNotValidException {
		employeeServiceImplementation.checker();
		modelAndView.addObject(FEEDBACKLIST, employeeServiceImplementation.fetchFeedback());
		modelAndView.setViewName(FEEDBACKLIST);
		return modelAndView;
	}

}
