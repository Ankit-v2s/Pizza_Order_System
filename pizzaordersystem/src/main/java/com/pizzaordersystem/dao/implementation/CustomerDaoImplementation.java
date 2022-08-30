package com.pizzaordersystem.dao.implementation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizzaordersystem.dao.CustomerDao;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PizzaOrder;

@Repository
public class CustomerDaoImplementation extends PizzaDaoImplementation implements CustomerDao{
	
	int orderId = 0;

	@Override
	public CustomerData getCustomer(LoginCredentials loginCredentials) throws SQLException {
		CustomerData customerData = new CustomerData();
		preparedStatement = connection
				.prepareStatement("select customer_id,customer_name,email,gender,address_line1,address_line2,"
						+ "city_name,state_name,country_name,phone_number from customer "
						+ "inner join city using(city_id)" + "inner join country using(country_id) "
						+ "inner join state where state.state_id=customer.state_id and customer_id=?;");
		preparedStatement.setInt(1, loginCredentials.getCustomerId());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			customerData.setCustomerId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			customerData.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			customerData.setEmail(resultSet.getString(resultSet.getMetaData().getColumnName(3)));
			customerData.setGender(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			customerData.setAddress1(resultSet.getString(resultSet.getMetaData().getColumnName(5)));
			customerData.setAddress2(resultSet.getString(resultSet.getMetaData().getColumnName(6)));
			customerData.setCity(resultSet.getString(resultSet.getMetaData().getColumnName(7)));
			customerData.setState(resultSet.getString(resultSet.getMetaData().getColumnName(8)));
			customerData.setCountry(resultSet.getString(resultSet.getMetaData().getColumnName(9)));
			customerData.setPhoneNumber(resultSet.getString(resultSet.getMetaData().getColumnName(10)));
		}
		return customerData;
	}

	@Override
	public List<FeedbackStatus> getFeedbackStatus(List<FeedbackStatus> feedbackStatusList) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM pizza_order.feedback_status;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			FeedbackStatus feedbackStatus = new FeedbackStatus();
			feedbackStatus.setFeedbackStatusId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			feedbackStatus.setFeedbackStatusType(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			feedbackStatusList.add(feedbackStatus);
		}
		return feedbackStatusList;
	}

	@Override
	public void updateCustomer(CustomerData customerData, int customerId) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"update customer set email=?,address_line1=?,address_line2=?,city_id=(select city_id from city where city_name=?),"
						+ "state_id=(select state_id from state where state_name=?),"
						+ "country_id=(select country_id from country where country_name=?),"
						+ "phone_number=? where customer_id=?;");
		preparedStatement.setString(1, customerData.getEmail());
		preparedStatement.setString(2, customerData.getAddress1());
		preparedStatement.setString(3, customerData.getAddress2());
		preparedStatement.setString(4, customerData.getCity());
		preparedStatement.setString(5, customerData.getState());
		preparedStatement.setString(6, customerData.getCountry());
		preparedStatement.setString(7, customerData.getPhoneNumber());
		preparedStatement.setInt(8, customerId);
		preparedStatement.executeUpdate();
	}

	@Override
	public void addFeedback(Feedback feedback, LoginCredentials loginCredentials) throws SQLException {
		preparedStatement = connection.prepareStatement("insert into feedback(customer_id,feedback_status_id,comments)"
				+ "values(?,(select feedback_status_id from feedback_status where feedback_status_type=?),?);");
		preparedStatement.setInt(1, loginCredentials.getCustomerId());
		preparedStatement.setString(2, feedback.getFeedbackStatusType());
		preparedStatement.setString(3, feedback.getComments());
		preparedStatement.executeUpdate();
	}

	@Override
	public int calculateAmount() throws SQLException {
		int amount = 0;
		preparedStatement = connection.prepareStatement("select sum(amount) from order_items where order_id=?;");
		preparedStatement.setInt(1, orderId);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			amount = resultSet.getInt(resultSet.getMetaData().getColumnName(1));
		}
		return amount;
	}

	@Override
	public void addOrder(LoginCredentials loginCredentials) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"insert into orders(customer_id,date_of_order) " + "values(?,curdate());",
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, loginCredentials.getCustomerId());
		preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			orderId = resultSet.getInt(1);
		}
	}

	@Override
	public void addItem(List<PizzaOrder> cart) throws SQLException {
		for (PizzaOrder pizzaOrder : cart) {
			preparedStatement = connection
					.prepareStatement("insert into order_items(order_id,pizza_id,quantity,amount) "
							+ "values(?,(select pizza_id from pizza_menu where pizza_name=?),?,"
							+ "((select price from pizza_menu where pizza_name=?)*?));");
			preparedStatement.setInt(1, orderId);
			preparedStatement.setString(2, pizzaOrder.getPizzaName());
			preparedStatement.setInt(3, pizzaOrder.getQuantity());
			preparedStatement.setString(4, pizzaOrder.getPizzaName());
			preparedStatement.setInt(5, pizzaOrder.getQuantity());
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public void addPayment(LoginCredentials credentials, Payment payment) throws SQLException {
		preparedStatement = connection
				.prepareStatement("insert into payment(mode_id,order_id,customer_id,amount,coupon_id) "
						+ "values((select mode_id from payment_modes where mode=?),?,?,?,(select coupon_id from coupons where coupon_code=?));");
		preparedStatement.setString(1, payment.getMode());
		preparedStatement.setInt(2, orderId);
		preparedStatement.setInt(3, credentials.getCustomerId());
		preparedStatement.setInt(4, payment.getAmount());
		preparedStatement.setString(5, payment.getCouponCode());
		preparedStatement.executeUpdate();
	}

	@Override
	public int discountPrice(PizzaOrder pizzaOrder) throws SQLException {
		int discount = 0;
		preparedStatement = connection.prepareStatement("select discount from coupons where coupon_code=?;");
		preparedStatement.setString(1, pizzaOrder.getCouponCode());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			discount = resultSet.getInt(resultSet.getMetaData().getColumnName(1));
		}
		return discount;
	}
}
