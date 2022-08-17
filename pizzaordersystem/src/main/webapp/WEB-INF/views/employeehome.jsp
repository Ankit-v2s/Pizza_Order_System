<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="employee.home.title" /></title>
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

			<div class="main-body table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-light table-hover">
					<tr>
						<td><strong>Order Id</strong></td>
						<td><strong>Customer Name</strong></td>
						<td><strong>Date Of Order</strong></td>
						<td><strong>Status</strong></td>
						<td><strong>Order Items Id</strong></td>
					</tr>
					<c:forEach items="${orderlist}" var="orders">
						<tr>
							<td>${orders.orderId}</td>
							<td>${orders.customerName}</td>
							<td>${orders.dateOfOrder}</td>
							<td>${orders.statusType}</td>
							<td>${orders.orderItemsId}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>