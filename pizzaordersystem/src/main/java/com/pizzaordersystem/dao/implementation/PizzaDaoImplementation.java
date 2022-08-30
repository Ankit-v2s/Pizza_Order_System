package com.pizzaordersystem.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizzaordersystem.dao.PizzzaDao;
import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.RegisterDetails;

@Repository
public class PizzaDaoImplementation implements PizzzaDao {

	static Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pizza_order", "root", "Password@123");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public List<LoginCredentials> login(List<LoginCredentials> credentialList) throws SQLException {
		preparedStatement = connection.prepareStatement("select * from login;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LoginCredentials loginCredentials = new LoginCredentials();
			loginCredentials.setLoginId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			loginCredentials.setUserName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			loginCredentials.setPassword(resultSet.getString(resultSet.getMetaData().getColumnName(3)));
			loginCredentials.setEmployeeId(resultSet.getInt(resultSet.getMetaData().getColumnName(4)));
			loginCredentials.setCustomerId(resultSet.getInt(resultSet.getMetaData().getColumnName(5)));
			credentialList.add(loginCredentials);
		}
		return credentialList;
	}

	@Override
	public List<City> getcity(List<City> cityList) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"select city_id,city_name,state_name,(select country_name from state inner join country using (country_id)"
						+ "where state_id=city.state_id)" + "from city inner join state using (state_id)");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			City city = new City();
			city.setCityId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			city.setCityName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			city.setStateName(resultSet.getString(resultSet.getMetaData().getColumnName(3)));
			city.setCountryName(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			cityList.add(city);
		}
		return cityList;
	}

	@Override
	public void addCustomer(RegisterDetails details) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"insert into customer(customer_name,address_line1,address_line2,city_id,state_id,country_id,"
						+ "gender,email,phone_number) values"
						+ "(?,?,?,(select city_id from city where city_name=?),(select state_id from state where state_name=?),"
						+ "(select country_id from country where country_name=?),?,?,?);");
		preparedStatement.setString(1, details.getName());
		preparedStatement.setString(2, details.getAddress1());
		preparedStatement.setString(3, details.getAddress2());
		preparedStatement.setString(4, details.getCity());
		preparedStatement.setString(5, details.getState());
		preparedStatement.setString(6, details.getCountry());
		preparedStatement.setString(7, details.getGender());
		preparedStatement.setString(8, details.getEmail());
		preparedStatement.setString(9, details.getPhoneNumber());
		preparedStatement.executeUpdate();

		preparedStatement = connection.prepareStatement("insert into login(username,password,customer_id)\n"
				+ "values(?,?,(select customer_id from customer where email=?));");
		preparedStatement.setString(1, details.getUserName());
		preparedStatement.setString(2, details.getPassword());
		preparedStatement.setString(3, details.getEmail());
		preparedStatement.executeUpdate();
	}
	
}
