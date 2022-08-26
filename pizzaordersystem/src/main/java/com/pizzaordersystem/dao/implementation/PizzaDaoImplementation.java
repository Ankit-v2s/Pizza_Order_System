package com.pizzaordersystem.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.pizzaordersystem.dao.PizzaDao;
import com.pizzaordersystem.model.City;
import com.pizzaordersystem.model.Coupon;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Employee;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.FeedbackStatus;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Order;
import com.pizzaordersystem.model.OrderStatus;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PaymentModes;
import com.pizzaordersystem.model.PizzaMenu;
import com.pizzaordersystem.model.PizzaOrder;
import com.pizzaordersystem.model.RegisterDetails;

@Repository
public class PizzaDaoImplementation implements PizzaDao {

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	int orderId = 0;

//	==========Connection==========
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
	
//	==========Login==========
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

//	==========SignUp==========
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

	
//	==========Admin==========
	@Override
	public List<Order> getOrders(List<Order> orderList) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select order_id,customer_name,date_of_order,status_type from orders "
						+ "inner join customer using (customer_id)" + "inner join order_status using(status_id)"
						+ "where date_of_order=curdate() order by order_id;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Order order = new Order();
			order.setOrderId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			order.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			order.setDateOfOrder(resultSet.getDate(resultSet.getMetaData().getColumnName(3)));
			order.setStatusType(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			orderList.add(order);
		}
		return orderList;
	}

	@Override
	public List<Order> getAllOrders(List<Order> orderList) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select order_id,customer_name,date_of_order,status_type from orders "
						+ "inner join customer using (customer_id) "
						+ "inner join order_status using(status_id) order by order_id;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Order order = new Order();
			order.setOrderId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			order.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			order.setDateOfOrder(resultSet.getDate(resultSet.getMetaData().getColumnName(3)));
			order.setStatusType(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			orderList.add(order);
		}
		return orderList;
	}

	@Override
	public List<OrderStatus> getOrderStatus(List<OrderStatus> orderStatusList) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM pizza_order.order_status;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			OrderStatus orderStatus = new OrderStatus();
			orderStatus.setStatusId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			orderStatus.setStatusType(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			orderStatusList.add(orderStatus);
		}
		return orderStatusList;
	}

	@Override
	public List<Order> getOrdersByStatusType(List<Order> orderList, String statusType) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select order_id,customer_name,date_of_order,status_type from orders "
						+ "inner join customer using (customer_id) "
						+ "inner join order_status using(status_id) where status_type =? order by order_id;");
		preparedStatement.setString(1, statusType);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Order order = new Order();
			order.setOrderId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			order.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			order.setDateOfOrder(resultSet.getDate(resultSet.getMetaData().getColumnName(3)));
			order.setStatusType(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			orderList.add(order);
		}
		return orderList;
	}

	@Override
	public List<Order> getOrdersByDate(List<Order> orderList, Date date) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select order_id,customer_name,date_of_order,status_type from orders "
						+ "inner join customer using (customer_id) "
						+ "inner join order_status using(status_id) where date_of_order =? order by order_id;");
		preparedStatement.setDate(1, date);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Order order = new Order();
			order.setOrderId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			order.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			order.setDateOfOrder(resultSet.getDate(resultSet.getMetaData().getColumnName(3)));
			order.setStatusType(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			orderList.add(order);
		}
		return orderList;
	}

	@Override
	public List<CustomerData> getCustomerData(List<CustomerData> customerDataList) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"select customer_id,customer_name,address_line1,address_line2,city_name,state_name,country_name,gender,"
						+ "email,phone_number from customer inner join city using(city_id)"
						+ "inner join country using(country_id)"
						+ "inner join state where state.state_id=customer.state_id \n" + "order by customer_id;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CustomerData customerData = new CustomerData();
			customerData.setCustomerId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			customerData.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			customerData.setAddress1(resultSet.getString(resultSet.getMetaData().getColumnName(3)));
			customerData.setAddress2(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			customerData.setCity(resultSet.getString(resultSet.getMetaData().getColumnName(5)));
			customerData.setState(resultSet.getString(resultSet.getMetaData().getColumnName(6)));
			customerData.setCountry(resultSet.getString(resultSet.getMetaData().getColumnName(7)));
			customerData.setGender(resultSet.getString(resultSet.getMetaData().getColumnName(8)));
			customerData.setEmail(resultSet.getString(resultSet.getMetaData().getColumnName(9)));
			customerData.setPhoneNumber(resultSet.getString(resultSet.getMetaData().getColumnName(10)));
			customerDataList.add(customerData);
		}
		return customerDataList;
	}

	@Override
	public List<Feedback> getFeedback(List<Feedback> feedbackList) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select feedback_id,customer_name,feedback_status_type,comments from feedback "
						+ "inner join customer using (customer_id)"
						+ "inner join feedback_status using(feedback_status_id)" + "group by feedback_id;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Feedback feedback = new Feedback();
			feedback.setFeedbackId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			feedback.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			feedback.setFeedbackStatusType(resultSet.getString(resultSet.getMetaData().getColumnName(3)));
			feedback.setComments(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			feedbackList.add(feedback);
		}
		return feedbackList;
	}

	@Override
	public List<PizzaMenu> getPizza(List<PizzaMenu> pizzaList) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM pizza_order.pizza_menu;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			PizzaMenu pizza = new PizzaMenu();
			pizza.setPizzaId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			pizza.setPizzaName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			pizza.setPrice(resultSet.getInt(resultSet.getMetaData().getColumnName(3)));
			pizzaList.add(pizza);
		}
		return pizzaList;
	}

	@Override
	public void addPizza(PizzaMenu pizzaMenu) throws SQLException {
		preparedStatement = connection.prepareStatement("insert into pizza_menu(pizza_name,price) " + "values(?,?);");
		preparedStatement.setString(1, pizzaMenu.getPizzaName());
		preparedStatement.setInt(2, pizzaMenu.getPrice());
		preparedStatement.executeUpdate();
	}

	@Override
	public PizzaMenu getPizza(int pizzaId) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM pizza_menu where pizza_id=?;");
		preparedStatement.setInt(1, pizzaId);
		resultSet = preparedStatement.executeQuery();
		PizzaMenu pizza = new PizzaMenu();
		while (resultSet.next()) {
			pizza.setPizzaId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			pizza.setPizzaName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			pizza.setPrice(resultSet.getInt(resultSet.getMetaData().getColumnName(3)));
		}
		return pizza;
	}

	@Override
	public void updatePizza(PizzaMenu pizzaMenu) throws SQLException {
		preparedStatement = connection
				.prepareStatement("update pizza_menu set pizza_name=?,price=? " + "where pizza_id=?;");
		preparedStatement.setString(1, pizzaMenu.getPizzaName());
		preparedStatement.setInt(2, pizzaMenu.getPrice());
		preparedStatement.setInt(3, pizzaMenu.getPizzaId());
		preparedStatement.executeUpdate();
	}

	@Override
	public void deletePizza(int pizzaId) throws SQLException {
		preparedStatement = connection.prepareStatement("delete from pizza_menu where pizza_id=?;");
		preparedStatement.setInt(1, pizzaId);
		preparedStatement.executeUpdate();
	}

	@Override
	public Employee getEmployee(LoginCredentials loginCredentials) throws SQLException {
		Employee employee = new Employee();
		preparedStatement = connection
				.prepareStatement("select employee_id,employee_name,employee_email,gender,address_line1,address_line2,"
						+ "city_name,state_name,country_name,phone_number from employee "
						+ "inner join city using(city_id)" + "inner join country using(country_id) "
						+ "inner join state where state.state_id=employee.state_id and employee_id=?;");
		preparedStatement.setInt(1, loginCredentials.getEmployeeId());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			employee.setEmployeeId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			employee.setEmployeeName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			employee.setEmail(resultSet.getString(resultSet.getMetaData().getColumnName(3)));
			employee.setGender(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			employee.setAddressLine1(resultSet.getString(resultSet.getMetaData().getColumnName(5)));
			employee.setAddressLine2(resultSet.getString(resultSet.getMetaData().getColumnName(6)));
			employee.setCityName(resultSet.getString(resultSet.getMetaData().getColumnName(7)));
			employee.setStateName(resultSet.getString(resultSet.getMetaData().getColumnName(8)));
			employee.setCountryName(resultSet.getString(resultSet.getMetaData().getColumnName(9)));
			employee.setPhoneNumber(resultSet.getString(resultSet.getMetaData().getColumnName(10)));
		}
		return employee;
	}

	@Override
	public List<Coupon> getCoupons(List<Coupon> couponList) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM pizza_order.coupons;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Coupon coupon = new Coupon();
			coupon.setCouponId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			coupon.setCouponCode(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			coupon.setDiscount(resultSet.getInt(resultSet.getMetaData().getColumnName(3)));
			couponList.add(coupon);
		}
		return couponList;
	}

	@Override
	public void addCoupon(Coupon coupon) throws SQLException {
		preparedStatement = connection.prepareStatement("insert into coupons(coupon_code,discount) " + "values(?,?);");
		preparedStatement.setString(1, coupon.getCouponCode());
		preparedStatement.setInt(2, coupon.getDiscount());
		preparedStatement.executeUpdate();
	}

	@Override
	public Coupon getcoupon(int couponId) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM coupons where coupon_id=?;");
		preparedStatement.setInt(1, couponId);
		resultSet = preparedStatement.executeQuery();
		Coupon coupon = new Coupon();
		while (resultSet.next()) {
			coupon.setCouponId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			coupon.setCouponCode(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			coupon.setDiscount(resultSet.getInt(resultSet.getMetaData().getColumnName(3)));
		}
		return coupon;
	}

	@Override
	public void updateCoupon(Coupon coupon) throws SQLException {
		preparedStatement = connection
				.prepareStatement("update coupons set coupon_code=?,discount=? " + "where coupon_id=?;");
		preparedStatement.setString(1, coupon.getCouponCode());
		preparedStatement.setInt(2, coupon.getDiscount());
		preparedStatement.setInt(3, coupon.getCouponId());
		preparedStatement.executeUpdate();
	}

	@Override
	public void deleteCoupon(int couponId) throws SQLException {
		preparedStatement = connection.prepareStatement("delete from coupons where coupon_id=?;");
		preparedStatement.setInt(1, couponId);
		preparedStatement.executeUpdate();
	}

	@Override
	public List<Payment> getPayments(List<Payment> paymentList) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select payment_id,customer_name,coupon_code,amount,mode from payment "
						+ "inner join customer using(customer_id) inner join coupons using(coupon_id)"
						+ "inner join payment_modes using(mode_id) " + "inner join orders using(order_id); ");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Payment payment = new Payment();
			payment.setPaymentId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			payment.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			payment.setCouponCode(resultSet.getString(resultSet.getMetaData().getColumnName(3)));
			payment.setAmount(resultSet.getInt(resultSet.getMetaData().getColumnName(4)));
			payment.setMode(resultSet.getString(resultSet.getMetaData().getColumnName(5)));
			paymentList.add(payment);
		}

		preparedStatement = connection.prepareStatement("select payment_id,customer_name,amount,mode from payment "
				+ "inner join customer using(customer_id) "
				+ "inner join payment_modes using(mode_id) inner join orders using(order_id) where coupon_id is null; ");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Payment payment = new Payment();
			payment.setPaymentId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			payment.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			payment.setCouponCode("null");
			payment.setAmount(resultSet.getInt(resultSet.getMetaData().getColumnName(3)));
			payment.setMode(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			paymentList.add(payment);
		}

		paymentList = paymentList.stream().sorted(Comparator.comparingInt(Payment::getPaymentId))
				.collect(Collectors.toList());

		return paymentList;
	}

	@Override
	public List<PaymentModes> getPaymentModes(List<PaymentModes> paymentModeList) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM pizza_order.payment_modes;");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			PaymentModes paymentModes = new PaymentModes();
			paymentModes.setModeId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			paymentModes.setMode(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			paymentModeList.add(paymentModes);
		}
		return paymentModeList;
	}

	@Override
	public List<Payment> getPaymentByMode(List<Payment> paymentList, String paymentMode) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select payment_id,customer_name,coupon_code,amount,mode from payment "
						+ "inner join customer using(customer_id) inner join coupons using(coupon_id)"
						+ "inner join payment_modes using(mode_id) "
						+ "inner join orders using(order_id) where mode=? ");
		preparedStatement.setString(1, paymentMode);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Payment payment = new Payment();
			payment.setPaymentId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			payment.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			payment.setCouponCode(resultSet.getString(resultSet.getMetaData().getColumnName(3)));
			payment.setAmount(resultSet.getInt(resultSet.getMetaData().getColumnName(4)));
			payment.setMode(resultSet.getString(resultSet.getMetaData().getColumnName(5)));
			paymentList.add(payment);
		}

		preparedStatement = connection.prepareStatement(
				"select payment_id,customer_name,amount,mode from payment " + "inner join customer using(customer_id) "
						+ "inner join payment_modes using(mode_id) inner join orders using(order_id) "
						+ "where coupon_id is null and mode=? ");
		preparedStatement.setString(1, paymentMode);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Payment payment = new Payment();
			payment.setPaymentId(resultSet.getInt(resultSet.getMetaData().getColumnName(1)));
			payment.setCustomerName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			payment.setCouponCode("null");
			payment.setAmount(resultSet.getInt(resultSet.getMetaData().getColumnName(3)));
			payment.setMode(resultSet.getString(resultSet.getMetaData().getColumnName(4)));
			paymentList.add(payment);
		}

		paymentList = paymentList.stream().sorted(Comparator.comparingInt(Payment::getPaymentId))
				.collect(Collectors.toList());
		return paymentList;
	}

	@Override
	public void updateEmployee(Employee employee,int employeeId) throws SQLException {
		preparedStatement = connection.prepareStatement("update employee set employee_email=?, address_line1=?,"
				+ "address_line2=?,city_id = (select city_id from city where city_name=?),state_id="
				+ "(select state_id from state where state_name=?), country_id="
				+ "(select country_id from country where country_name=?), phone_number=? where employee_id=?;");
		preparedStatement.setString(1, employee.getEmail());
		preparedStatement.setString(2, employee.getAddressLine1());
		preparedStatement.setString(3, employee.getAddressLine2());
		preparedStatement.setString(4, employee.getCityName());
		preparedStatement.setString(5, employee.getStateName());
		preparedStatement.setString(6, employee.getCountryName());
		preparedStatement.setString(7, employee.getPhoneNumber());
		preparedStatement.setInt(8, employeeId);
		preparedStatement.executeUpdate();
	}

//	==========Customer==========
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
	public int pizzaOrder() throws SQLException {
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
