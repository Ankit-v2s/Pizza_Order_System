<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value="/static/css/pizzaorder.css" />" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<%@include file="employeenavbar.jsp"%>
			<div class="main-body">
				<table class="table table-hover table-light" style="width:70%">
					<tr>
						<td><strong>Customer Id</strong></td>
						<td><strong>Customer Name</strong></td>
						<td><strong>Address Line 1</strong></td>
						<td><strong>Address Line 2</strong></td>
						<td><strong>City Name</strong></td>
						<td><strong>State Name</strong></td>
						<td><strong>Country Name</strong></td>
						<td><strong>Gender</strong></td>
						<td><strong>Email</strong></td>
						<td><strong>Phone Number</strong></td>
					</tr>
					<c:forEach items="${customerlist}" var="customer">
						<tr>
							<td>${customer.customerId}</td>
							<td>${customer.customerName}</td>
							<td>${customer.address1}</td>
							<td>${customer.address2}</td>
							<td>${customer.city}</td>
							<td>${customer.state}</td>
							<td>${customer.country}</td>
							<td>${customer.gender}</td>
							<td>${customer.email}</td>
							<td>${customer.phoneNumber}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>