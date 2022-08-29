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
			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-hover table-light">
					<tr>
						<td><strong><spring:message code="employee.feedback.table.heading.id" /></strong></td>
						<td><strong><spring:message code="employee.feedback.table.heading.name" /></strong></td>
						<td><strong><spring:message code="employee.feedback.table.heading.type" /></strong></td>
						<td><strong><spring:message code="employee.feedback.table.heading.comments" /></strong></td>
					</tr>
					<c:forEach items="${feedbacklist}" var="feedback">
						<tr>
							<td>${feedback.feedbackId}</td>
							<td>${feedback.customerName}</td>
							<td>${feedback.feedbackStatusType}</td>
							<td>${feedback.comments}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>