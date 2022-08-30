package com.pizzaordersystem.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.RegisterDetails;

public interface PizzzaDao {

	//	==========Connection==========
	Connection getConnection() throws ClassNotFoundException;

	void close();

	//	==========Login==========
	List<LoginCredentials> login(List<LoginCredentials> credentialList) throws SQLException;

	//	==========SignUp==========
	List<City> getcity(List<City> cityList) throws SQLException;

	void addCustomer(RegisterDetails details) throws SQLException;

}