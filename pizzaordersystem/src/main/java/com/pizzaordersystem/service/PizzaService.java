package com.pizzaordersystem.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidCredentialException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.RegisterDetails;

/**
 * @author Ankit Madhavi
 *
 */
public interface PizzaService {

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
	 */
	String credentialChecker(LoginCredentials loginCredentials, BindingResult result, HttpServletRequest request)
			throws SQLException, InvalidCredentialException, InvalidFieldException, CredentialsNotValidException;

	/**
	 * To add new customer
	 * 
	 * @param details
	 * @param result
	 * @throws SQLException
	 * @throws InvalidFieldException
	 */
	void addCustomer(RegisterDetails details, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 * To fetch the cities in list
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<City> fetchCity() throws SQLException;

	/**
	 * To fetch the details of the particular city
	 * 
	 * @param city
	 * @return City
	 * @throws SQLException
	 */
	City fetchCityDetails(String city) throws SQLException;

	/**
	 * To logout and close the connection with the database
	 */
	void logout();

}