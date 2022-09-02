package com.pizzaordersystem.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class HomeController {

	private static final String CITY_LIST = "cityList";

	private static final String LOGIN = "login";
	
	@Autowired
	private PizzaService pizzaServiceImplementation;

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * To load the application and open the first page 
	 * Logout functionality
	 */ 
	@RequestMapping("/")
	public ModelAndView loginPage(ModelAndView modelAndView) {
		pizzaServiceImplementation.logout();
		modelAndView.setViewName(LOGIN);
		return modelAndView;
	}

	/**
	 * @param loginCredentials
	 * @param result
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidCredentialException
	 * @throws InvalidFieldException      
	 * @throws CredentialsNotValidException 
	 * API to check credentials
	 * 
	 */
	@PostMapping("/login")
	public String checkLogin(@Valid @RequestBody LoginCredentials loginCredentials, BindingResult result)
			throws ClassNotFoundException, SQLException, InvalidCredentialException, InvalidFieldException, CredentialsNotValidException {
		pizzaServiceImplementation.createConnection();
		return pizzaServiceImplementation.credentialChecker(loginCredentials, result);
	}

	/**
	 * @param modelAndView
	 * @return ModelAndView
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * Open Sign up page
	 */
	@GetMapping("/signup")
	public ModelAndView signUp(ModelAndView modelAndView) throws ClassNotFoundException, SQLException {
		pizzaServiceImplementation.createConnection();
		modelAndView.addObject(CITY_LIST, pizzaServiceImplementation.fetchCity());
		modelAndView.setViewName("signup");
		return modelAndView;
	}

	/**
	 * @param details
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException 
	 * To add Customer
	 */
	@PostMapping("/add/customer")
	public void addCustomer(@Valid @RequestBody RegisterDetails details, BindingResult result)
			throws SQLException, InvalidFieldException {
		pizzaServiceImplementation.addCustomer(details, result);
	}

	/**
	 * @param city
	 * @return City
	 * @throws SQLException
	 * To get the city details
	 */
	@GetMapping("/city/{city}")
	public City loadCityDetails(@PathVariable String city) throws SQLException {
		return pizzaServiceImplementation.fetchCityDetails(city);
	}
}
