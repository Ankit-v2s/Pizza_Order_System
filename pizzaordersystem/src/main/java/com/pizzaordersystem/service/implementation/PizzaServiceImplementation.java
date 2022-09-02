package com.pizzaordersystem.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pizzaordersystem.dao.PizzzaDao;
import com.pizzaordersystem.exception.InvalidCredentialException;
import com.pizzaordersystem.exception.CredentialsNotValidException;
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
@Service
public class PizzaServiceImplementation implements PizzaService {

	@Autowired
	private PizzzaDao pizzaDaoImplementation;
	
	private static final String CUSTOMERHOME = "customerhome";
	private static final String EMPLOYEEHOME = "employeehome";
	private static final String INVALID_CREDENTIALS = "Invalid Credentials";
	
	static LoginCredentials loginCredentials;
	static PizzaOrder pizzaOrder;
	static List<PizzaOrder> cart = new ArrayList<>();
	static String roles;
	Connection connection;
	
	
	/**
	 *@return List
	 *getter for the List cart
	 */
	public List<PizzaOrder> getCartList() {
		return cart;
	}

	/**
	 * @param credentials
	 * @return String
	 * To return the employee or customer homepage
	 */
	private String setCredentialsAndReturnPage(LoginCredentials credentials) {
		PizzaServiceImplementation.loginCredentials=credentials;
		roles=credentials.getRoles();
		if (credentials.getEmployeeId() != 0) {
			return EMPLOYEEHOME;
		} else if (credentials.getCustomerId() != 0) {
			return CUSTOMERHOME;
		}
		throw new NullPointerException();
	}
	
	/**
	 *@throws ClassNotFoundException
	 *To make a connection database
	 */
	@Override
	public void createConnection() throws ClassNotFoundException {
		this.connection = pizzaDaoImplementation.getConnection();
	}

	/**
	 *@param loginCredentials
	 *@param result
	 *@return String
	 *@throws SQLException
	 *@throws InvalidCredentialException
	 *@throws InvalidFieldException
	 *To check the credentials
	 * @throws CredentialsNotValidException 
	 */
	@Override
	public String credentialChecker(LoginCredentials loginCredentials, BindingResult result)
			throws SQLException, InvalidCredentialException, InvalidFieldException, CredentialsNotValidException {
		List<LoginCredentials> credentialList = new ArrayList<>();
		if (!result.hasErrors()) {
			for (LoginCredentials credentials : pizzaDaoImplementation.login(credentialList)) {
				if (credentials.getUserName().equals(loginCredentials.getUserName())
						&& credentials.getPassword().equals(loginCredentials.getPassword())) {
					return setCredentialsAndReturnPage(credentials);
				}
			}
			throw new InvalidCredentialException(INVALID_CREDENTIALS);
		}
		throw new InvalidFieldException(result);
	}

	/**
	 *@param details
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To add new customer 
	 */
	@Override
	public void addCustomer(@Valid RegisterDetails details, BindingResult result)
			throws SQLException, InvalidFieldException {
		if (!result.hasErrors()) {
			pizzaDaoImplementation.addCustomer(details);
		} else {
			throw new InvalidFieldException(result);
		}
	}

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the cities in list
	 */
	@Override
	public List<City> fetchCity() throws SQLException {
		List<City> cityList = new ArrayList<>();
		return pizzaDaoImplementation.getcity(cityList);
	}

	/**
	 *@param city
	 *@return City
	 *@throws SQLException
	 *To fetch the details of the particular city
	 */
	@Override
	public City fetchCityDetails(String city) throws SQLException {
		for (City cityDetails : fetchCity()) {
			if (cityDetails.getCityName().equals(city)) {
				return cityDetails;
			}
		}
		throw new NullPointerException();
	}

	/**
	 *To logout and close the connection with the database
	 */
	@Override
	public void logout() {
		pizzaDaoImplementation.close();
	}

	
}
