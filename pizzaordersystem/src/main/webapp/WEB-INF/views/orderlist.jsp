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
				<div class="row m-auto p-4 mt-2">
					<div class="col-md-12">
						<div class="form-row">
							<div class="col-md-4">
								<input type='date' class="txtbox" id="dateOfOrder"
									onchange="filterOrderByDate()" />
							</div>
							<div class="col-md-4">
								<select class="txtbox" style="margin-left: 135px" id="orderstatus"
									name="orderstatus" onchange="filterOrderByStatus()">
									<option selected value=""><spring:message code="employee.order.status.default.option" /></option>
									<c:forEach items="${orderstatuslist}" var="orderstatus">
										<option value="${orderstatus.statusType}">${orderstatus.statusType}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>

			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-hover table-light">
					<tr>
						<td><strong><spring:message code="employee.order.table.heading.id" /></strong></td>
						<td><strong><spring:message code="employee.order.table.heading.name" /></strong></td>
						<td><strong><spring:message code="employee.order.table.heading.date" /></strong></td>
						<td><strong><spring:message code="employee.order.table.heading.status" /></strong></td>
					</tr>
					<c:forEach items="${fullorderlist}" var="orders">
						<tr>
							<td>${orders.orderId}</td>
							<td>${orders.customerName}</td>
							<td>${orders.dateOfOrder}</td>
							<td>${orders.statusType}</td>
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
		src="<c:url value="/static/js/orders.js" />"></script>
</body>
</html>