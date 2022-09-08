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
			<li><a class="main-header-nav" href="/pizzaordersystem/customerhome"><spring:message code="employee.header.main" /></a></li>
			<li><a href="/pizzaordersystem/editcustomer"><spring:message code="customer.home.nav.edit" /></a></li>
			<li><a href="/pizzaordersystem/feedback"><spring:message code="customer.home.nav.feedback" /></a></li>
			<li><a href="/pizzaordersystem/logout"><spring:message code="employee.home.nav.logout" /></a></li>
		</ul>
	</body>
</html>