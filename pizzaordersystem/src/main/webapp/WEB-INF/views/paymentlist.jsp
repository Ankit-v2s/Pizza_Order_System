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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
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
				<div class="row m-auto p-4 mt-2">
					<div class="col-md-6 offset-md-3">
						<div class="form-group">
							<select class="txtbox col-md-5" id="paymentmode"
								name="paymentmode" onchange="filterPaymentByMode()">
								<option selected disabled value=""><spring:message code="employee.payment.mode.default.option" /></option>
								<c:forEach items="${paymentmodelist}" var="paymentmode">
									<option value="${paymentmode.mode}">${paymentmode.mode}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-hover table-light">
					<tr>
						<td><strong><spring:message code="employee.payment.table.heading.id" /></strong></td>
						<td><strong><spring:message code="employee.payment.table.heading.name" /></strong></td>
						<td><strong><spring:message code="employee.payment.table.heading.coupon" /></strong></td>
						<td><strong><spring:message code="employee.payment.table.heading.amount" /></strong></td>
						<td><strong><spring:message code="employee.payment.table.heading.mode" /></strong></td>
					</tr>
					<c:forEach items="${paymentlist}" var="payment">
						<tr>
							<td>${payment.paymentId}</td>
							<td>${payment.customerName}</td>
							<td>${payment.couponCode}</td>
							<td>${payment.amount}</td>
							<td>${payment.mode}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/static/js/login.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/static/js/payments.js" />"></script>
</body>
</html>