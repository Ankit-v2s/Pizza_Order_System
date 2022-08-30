package com.pizzaordersystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.pizzaordersystem.exception.CredentialCheckerException;
import com.pizzaordersystem.exception.CredentialsNotValidException;
import com.pizzaordersystem.exception.InvalidFieldException;
import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.RegisterDetails;

public interface PizzaService {

	/**
	 *@throws ClassNotFoundException
	 *To make a connection database
	 */
	void createConnection() throws ClassNotFoundException;

	//	==========Login==========
	/**
	 *@param loginCredentials
	 *@param result
	 *@return String
	 *@throws SQLException
	 *@throws CredentialCheckerException
	 *@throws InvalidFieldException
	 *To check the credentials
	 */
	String credentialChecker(LoginCredentials loginCredentials, BindingResult result)
			throws SQLException, CredentialCheckerException, InvalidFieldException, CredentialsNotValidException;

	//	==========SignUp==========
	/**
	 *@param details
	 *@param result
	 *@throws SQLException
	 *@throws InvalidFieldException
	 *To add new customer 
	 */
	void addCustomer(RegisterDetails details, BindingResult result) throws SQLException, InvalidFieldException;

	/**
	 *@return List
	 *@throws SQLException
	 *To fetch the cities in list
	 */
	List<City> fetchCity() throws SQLException;

	/**
	 *@param city
	 *@return City
	 *@throws SQLException
	 *To fetch the details of the particular city
	 */
	City fetchCityDetails(String city) throws SQLException;

	//	==========Logout==========
	/**
	 *To logout and close the connection with the database
	 */
	void logout();

}