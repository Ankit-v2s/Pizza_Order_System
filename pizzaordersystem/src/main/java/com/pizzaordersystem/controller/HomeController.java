package com.pizzaordersystem.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidCredentialException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.RegisterDetails;
import com.pizzaordersystem.service.PizzaService;

/**
 * @author Ankit Madhavi
 *
 */
@RestController
@SessionAttributes("credentials")
public class HomeController {

	private static final String CITY_LIST = "cityList";

	private static final String LOGIN = "login";

	@Autowired
	@Qualifier("pizzaServiceImplementation")
	private PizzaService pizzaService;

	/**
	 * To load the application and open the first page
	 * 
	 * @param modelAndView
	 * @param status
	 * @return ModelAndView
	 */
	@RequestMapping("/")
	public ModelAndView loginPage(ModelAndView modelAndView) {
		modelAndView.setViewName(LOGIN);
		return modelAndView;
	}

	/**
	 * To destroy the session
	 * 
	 * @param status
	 */
	@GetMapping("/session-close")
	public void closeSession(SessionStatus status) {
		status.setComplete();
	}

	/**
	 * API to check credentials
	 * 
	 * @param loginCredentials
	 * @param result
	 * @param request
	 * @return String
	 * @throws SQLException
	 * @throws InvalidCredentialException
	 * @throws InvalidFieldException
	 * @throws CredentialsNotValidException
	 */
	@PostMapping("/login")
	public String checkLogin(@Valid @RequestBody LoginCredentials loginCredentials, BindingResult result,
			HttpServletRequest request)
			throws SQLException, InvalidCredentialException, InvalidFieldException, CredentialsNotValidException {
		return pizzaService.credentialChecker(loginCredentials, result, request);
	}

	/**
	 * Open Sign up page
	 * 
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws SQLException
	 */
	@GetMapping("/signup")
	public ModelAndView signUp(ModelAndView modelAndView) throws SQLException {
		modelAndView.addObject(CITY_LIST, pizzaService.fetchCity());
		modelAndView.setViewName("signup");
		return modelAndView;
	}

	/**
	 * To add Customer
	 * 
	 * @param details
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	@PostMapping("/add/customer")
	public void addCustomer(@Valid @RequestBody RegisterDetails details, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaService.addCustomer(details, result);
	}

	/**
	 * To get the city details
	 * 
	 * @param city
	 * @return City
	 * @throws SQLException
	 */
	@GetMapping("/city/{city}")
	public City loadCityDetails(@PathVariable String city) throws SQLException {
		return pizzaService.fetchCityDetails(city);
	}
	
	/**
	 * To logout and return to login page
	 * 
	 * @param status
	 * @param modelAndView
	 * @return ModelAndView
	 */
	@GetMapping("/logout")
	public ModelAndView logout(SessionStatus status,ModelAndView modelAndView) {
		pizzaService.logout();
		closeSession(status);
		return loginPage(modelAndView);
	}
}
