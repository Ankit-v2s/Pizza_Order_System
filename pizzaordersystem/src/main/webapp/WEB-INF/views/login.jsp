<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/static/css/pizzaorder.css" />" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title><spring:message code="login.title" /></title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="col-md-12">
			<h1 class="main-header">
				<spring:message code="login.header.main" />
			</h1>
			<h3 class="sub-hader">
				<spring:message code="login.header.secondary" />
			</h3>
		</div>
		   
		<div class="col-md-12 login-body">
		<div id="credentialError" class="error">${error}</div>
			<div class="form-group ">
				<input type="text" class="login-txtbox" id="userName" name="userName" placeholder=<spring:message code="login.placeholder.username" />>
				<div id="usernameError" class="error"></div>
			</div>
			<div class="form-group ">
				<input type="password" class="login-txtbox" id="password" name="password" placeholder=<spring:message code="login.placeholder.password" />>
				<div id="passwordError" class="error"></div> 
			</div>
			<div class="form-group ">
				<input class="btn" type="submit" id="login" value=<spring:message code="login.placeholder.button" />>
			</div>
			<div class="form-group ">
				<a href="signup" class="link"><spring:message code="login.hyperlink.regestration" /></a> 
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/static/js/login.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/static/js/validation.js" />"></script>
</body>
</html>