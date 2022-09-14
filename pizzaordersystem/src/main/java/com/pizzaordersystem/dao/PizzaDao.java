package com.pizzaordersystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.RegisterDetails;

/**
 * @author Ankit Madhavi
 *
 */
public interface PizzaDao {

	/**
	 *Close the resources
	 */
	void close();
	
	/**
	 *@param credentialList
	 *@return List
	 *@throws SQLException
	 *To get all the credentials
	 */
	List<LoginCredentials> login(List<LoginCredentials> credentialList) throws SQLException;

	/**
	 *@param cityList
	 *@return List
	 *@throws SQLException
	 *To get all the city details
	 */
	List<City> getcity(List<City> cityList) throws SQLException;

	/**
	 *@param details
	 *@throws SQLException
	 *To insert new customer to table
	 */
	void addCustomer(RegisterDetails details) throws SQLException;

}