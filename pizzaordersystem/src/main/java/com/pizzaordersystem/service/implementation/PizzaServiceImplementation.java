package com.pizzaordersystem.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.PizzaDao;
import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidCredentialException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.model.RegisterDetails;
import com.pizzaordersystem.service.PizzaService;

/**
 * @author Ankit Madhavi
 *
 */
@Service("pizzaServiceImplementation")
public class PizzaServiceImplementation implements PizzaService {

	@Autowired
	@Qualifier("pizzaDaoImplementation")
	private PizzaDao pizzaDao;

	private static final String CUSTOMERHOME = "customerhome";
	private static final String EMPLOYEEHOME = "employeehome";
	private static final String INVALID_CREDENTIALS = "Invalid Credentials";

	static LoginCredentials loginCredentials;
	static PizzaOrder pizzaOrder;
	static List<PizzaOrder> cart = new ArrayList<>();
	static String roles;
	Connection connection;

	/**
	 * @return List getter for the List cart
	 */
	public List<PizzaOrder> getCartList() {
		return cart;
	}

	/**
	 * To return the employee or customer homepage
	 * 
	 * @param credentials
	 * @return String
	 */
	private String setCredentialsAndReturnPage(LoginCredentials credentials) {
		PizzaServiceImplementation.loginCredentials = credentials;
		roles = credentials.getRoles();
		if (credentials.getEmployeeId() != 0) {
			return EMPLOYEEHOME;
		} else if (credentials.getCustomerId() != 0) {
			return CUSTOMERHOME;
		}
		throw new NullPointerException();
	}

	/**
	 * To make a connection database
	 * 
	 * @throws ClassNotFoundException
	 */
	@Override
	public void createConnection() throws ClassNotFoundException {
		this.connection = pizzaDao.getConnection();
	}

	/**
	 * To check the credentials
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
	@Override
	public String credentialChecker(LoginCredentials loginCredentials, BindingResult result, HttpServletRequest request)
			throws SQLException, InvalidCredentialException, InvalidFieldException, CredentialsNotValidException {
		List<LoginCredentials> credentialList = new ArrayList<>();
		if (!result.hasErrors()) {
			for (LoginCredentials credentials : pizzaDao.login(credentialList)) {
				if (credentials.getUserName().equals(loginCredentials.getUserName())
						&& credentials.getPassword().equals(loginCredentials.getPassword())) {
					HttpSession session =  request.getSession();
					session.setAttribute("credentials", credentials);
					return setCredentialsAndReturnPage(credentials);
				}
			}
			throw new InvalidCredentialException(INVALID_CREDENTIALS);
		}
		throw new InvalidFieldException(result);
	}

	/**
	 * To add new customer
	 * 
	 * @param details
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	@Override
	public void addCustomer(@Valid RegisterDetails details, BindingResult result)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaDao.addCustomer(details);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 * To fetch the cities in list
	 * 
	 * @return List
	 * @throws SQLException
	 */
	@Override
	public List<City> fetchCity() throws SQLException {
		List<City> cityList = new ArrayList<>();
		return pizzaDao.getcity(cityList);
	}

	/**
	 * To fetch the details of the particular city
	 * 
	 * @param city
	 * @return City
	 * @throws SQLException
	 */
	@Override
	public City fetchCityDetails(String city) throws SQLException {
		return fetchCity().stream().filter(cityDetails -> cityDetails.getCityName().equalsIgnoreCase(city)).findFirst()
				.orElseThrow();
	}

	/**
	 * To logout and close the connection with the database
	 */
	@Override
	public void logout() {
		pizzaDao.close();
	}

}
