package com.pizzaordersystem.dao.implementation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.pizzaordersystem.dao.EmployeeDao;
import com.pizzaordersystem.model.Coupon;
import com.pizzaordersystem.model.CustomerData;
import com.pizzaordersystem.model.Employee;
import com.pizzaordersystem.model.Feedback;
import com.pizzaordersystem.model.LoginCredentials;
import com.pizzaordersystem.model.Order;
import com.pizzaordersystem.model.OrderStatus;
import com.pizzaordersystem.model.Payment;
import com.pizzaordersystem.model.PaymentModes;
import com.pizzaordersystem.model.PizzaMenu;

/**
 * @author Ankit Madhavi
 *
 */
@Repository
public class EmployeeDaoImplementation extends PizzaDaoImplementation implements EmployeeDao {
	
	/**
	 *@param orderList
	 *@return List
	 *@throws SQLException
	 *To get all the orders according to the today's date
	 */
	@Override
	public List<Order> getOrders(List<Order> orderList) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select order_id,customer_name,date_of_order,status_type from orders "
						+ "inner join customer using (customer_id) inner join order_status using(status_id)"
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

	/**
	 *@param orderList
	 *@return List
	 *@throws SQLException
	 *To get all the orders
	 */
	@Override
	public List<Order> getAllOrders(List<Order> orderList) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select order_id,customer_name,date_of_order,status_type from orders "
						+ "inner join customer using (customer_id) inner join order_status using(status_id) order by order_id;");
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

	/**
	 *@param orderStatusList
	 *@return List
	 *@throws SQLException
	 *To get all the order status available
	 */
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

	/**
	 *@param orderList
	 *@param statusType
	 *@return List
	 *@throws SQLException
	 *To get all the orders according to the status type
	 */
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

	/**
	 *@param orderList
	 *@param date
	 *@return List
	 *@throws SQLException
	 *To get all the orders according to the date selected
	 */
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

	/**
	 *@param customerDataList
	 *@return List
	 *@throws SQLException
	 *To get all the customers details available
	 */
	@Override
	public List<CustomerData> getCustomerData(List<CustomerData> customerDataList) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"select customer_id,customer_name,address_line1,address_line2,city_name,state_name,country_name,gender,"
						+ "email,phone_number from customer inner join city using(city_id)"
						+ "inner join country using(country_id)"
						+ "inner join state where state.state_id=customer.state_id order by customer_id;");
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

	/**
	 *@param feedbackList
	 *@return List
	 *@throws SQLException
	 *To get all the feedback details available
	 */
	@Override
	public List<Feedback> getFeedback(List<Feedback> feedbackList) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select feedback_id,customer_name,feedback_status_type,comments from feedback "
						+ "inner join customer using (customer_id)"
						+ "inner join feedback_status using(feedback_status_id) group by feedback_id;");
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

	/**
	 *@param pizzaList
	 *@return List
	 *@throws SQLException
	 *To get all the pizza details available
	 */
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

	/**
	 *@param pizzaMenu
	 *@throws SQLException
	 *To insert new pizza to the table
	 */
	@Override
	public void addPizza(PizzaMenu pizzaMenu) throws SQLException {
		preparedStatement = connection.prepareStatement("insert into pizza_menu(pizza_name,price) values(?,?);");
		preparedStatement.setString(1, pizzaMenu.getPizzaName());
		preparedStatement.setInt(2, pizzaMenu.getPrice());
		preparedStatement.executeUpdate();
	}

	/**
	 *@param pizzaId
	 *@return PizzaMenu
	 *@throws SQLException
	 *To get the pizza details according to the selected pizza
	 */
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

	/**
	 *@param pizzaMenu
	 *@throws SQLException
	 *To update the pizza details of particular pizza
	 */
	@Override
	public void updatePizza(PizzaMenu pizzaMenu) throws SQLException {
		preparedStatement = connection
				.prepareStatement("update pizza_menu set pizza_name=?,price=? where pizza_id=?;");
		preparedStatement.setString(1, pizzaMenu.getPizzaName());
		preparedStatement.setInt(2, pizzaMenu.getPrice());
		preparedStatement.setInt(3, pizzaMenu.getPizzaId());
		preparedStatement.executeUpdate();
	}

	/**
	 *@param pizzaId
	 *@throws SQLException
	 *To delete the selected pizza
	 */
	@Override
	public void deletePizza(int pizzaId) throws SQLException {
		preparedStatement = connection.prepareStatement("delete from pizza_menu where pizza_id=?;");
		preparedStatement.setInt(1, pizzaId);
		preparedStatement.executeUpdate();
	}

	/**
	 *@param loginCredentials
	 *@return Employee
	 *@throws SQLException
	 *To get the details of specific employee
	 */
	@Override
	public Employee getEmployee(LoginCredentials loginCredentials) throws SQLException {
		Employee employee = new Employee();
		preparedStatement = connection
				.prepareStatement("select employee_id,employee_name,employee_email,gender,address_line1,address_line2,"
						+ "city_name,state_name,country_name,phone_number from employee "
						+ "inner join city using(city_id) inner join country using(country_id) "
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

	/**
	 *@param couponList
	 *@return List
	 *@throws SQLException
	 *To get all the coupons available
	 */
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

	/**
	 *@param coupon
	 *@throws SQLException
	 *To insert new coupon to table
	 */
	@Override
	public void addCoupon(Coupon coupon) throws SQLException {
		preparedStatement = connection.prepareStatement("insert into coupons(coupon_code,discount) values(?,?);");
		preparedStatement.setString(1, coupon.getCouponCode());
		preparedStatement.setInt(2, coupon.getDiscount());
		preparedStatement.executeUpdate();
	}

	/**
	 *@param couponId
	 *@return Coupon
	 *@throws SQLException
	 *To get the coupon details of the selected coupon
	 */
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

	/**
	 *@param coupon
	 *@throws SQLException
	 *To update the specific coupon details
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws SQLException {
		preparedStatement = connection
				.prepareStatement("update coupons set coupon_code=?,discount=? where coupon_id=?;");
		preparedStatement.setString(1, coupon.getCouponCode());
		preparedStatement.setInt(2, coupon.getDiscount());
		preparedStatement.setInt(3, coupon.getCouponId());
		preparedStatement.executeUpdate();
	}

	/**
	 *@param couponId
	 *@throws SQLException
	 *To delete the specific coupon
	 */
	@Override
	public void deleteCoupon(int couponId) throws SQLException {
		preparedStatement = connection.prepareStatement("delete from coupons where coupon_id=?;");
		preparedStatement.setInt(1, couponId);
		preparedStatement.executeUpdate();
	}

	/**
	 *@param paymentList
	 *@return List
	 *@throws SQLException
	 *To get all the payments available in the table
	 */
	@Override
	public List<Payment> getPayments(List<Payment> paymentList) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select payment_id,customer_name,coupon_code,amount,mode from payment "
						+ "inner join customer using(customer_id) inner join coupons using(coupon_id)"
						+ "inner join payment_modes using(mode_id) inner join orders using(order_id); ");
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
				+ "inner join payment_modes using(mode_id) inner join orders using(order_id) where coupon_id is null;");
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

	/**
	 *@param paymentModeList
	 *@return List
	 *@throws SQLException
	 *To get all the payments modes available in the table
	 */
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

	/**
	 *@param paymentList
	 *@param paymentMode
	 *@return List
	 *@throws SQLException
	 *To get all the payments according to payment mode 
	 */
	@Override
	public List<Payment> getPaymentByMode(List<Payment> paymentList, String paymentMode) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select payment_id,customer_name,coupon_code,amount,mode from payment "
						+ "inner join customer using(customer_id) inner join coupons using(coupon_id)"
						+ "inner join payment_modes using(mode_id) "
						+ "inner join orders using(order_id) where mode=?;");
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
						+ "where coupon_id is null and mode=?;");
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

	/**
	 *@param employee
	 *@param employeeId
	 *@throws SQLException
	 *To update the details for the employee
	 */
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

}
