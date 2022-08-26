<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a class="main-header-nav" href="/pizzaordersystem/employeehome"><spring:message code="employee.header.main" /></a></li>
		<li><a href="/pizzaordersystem/pizza"><spring:message code="employee.home.nav.pizza" /></a></li>
		<li><a href="/pizzaordersystem/customer"><spring:message code="employee.home.nav.customer" /></a></li>
		<li><a href="/pizzaordersystem/employeedetails"><spring:message code="employee.home.nav.employee.details" /></a></li>
		<li><a href="/pizzaordersystem/coupons"><spring:message code="employee.home.nav.coupons" /></a></li>
		<li><a href="/pizzaordersystem/payments"><spring:message code="employee.home.nav.payments" /></a></li>
		<li><a href="/pizzaordersystem/orders"><spring:message code="employee.home.nav.orders" /></a></li>
		<li><a href="/pizzaordersystem/feedbacks"><spring:message code="employee.home.nav.feedbacks" /></a></li>
		<li><a href="/pizzaordersystem/"><spring:message code="employee.home.nav.logout" /></a></li>
	</ul>
</body>
</html>