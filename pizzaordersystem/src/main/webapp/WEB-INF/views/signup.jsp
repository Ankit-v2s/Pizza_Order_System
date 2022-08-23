<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="signup.title" /></title>
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
	<div class="container mt-2">
		<div class="row m-auto p-2 mt-1">
			<div class="col-md-6 jumbotron offset-md-3 sign-up-jubmotron">
				<h3 class="text-center"><spring:message code="signup.header" /></h3>
				<div class="signup-wrapper-scroll-y signup-custom-scrollbar">
				<div class="form-group">
					<label for="name"><spring:message code="signup.label.name" /></label> <input type="text"
						class="form-control" id="name" name="name" placeholder="<spring:message code="signup.placeholder.name" />">
					<div id="nameError" class="error"></div>
				</div>
				<div class="form-group">
					<label for="inputAddress"><spring:message code="signup.label.address1" /></label> <input type="text"
						class="form-control" id="addressLine1" name="addressLine1"
						placeholder="<spring:message code="signup.placeholder.address1" />">
					<div id="address1Error" class="error"></div>
				</div>
				<div class="form-group">
					<label for="inputAddress2"><spring:message code="signup.label.address2" /></label> <input
						type="text" class="form-control" id="addressLine2"
						name="addressLine2" placeholder="<spring:message code="signup.placeholder.address2" />">
					<div id="address2Error" class="error"></div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="city"><spring:message code="signup.label.city" /></label> <select class="form-control"
							id="city" onchange="getCityDetails()" name="city">
							<option selected disabled value=""><spring:message code="signup.city.disabled.option" /></option>
							<c:forEach items="${cityList}" var="city">
								<option value="${city.cityName}">${city.cityName}</option>
							</c:forEach>
						</select>
						<div id="cityError" class="error"></div>
					</div>
					<div class="form-group col-md-6">
						<label for="state"><spring:message code="signup.label.state" /></label> <input type="text"
							disabled="disabled" class="form-control" id="state" name="state"
							placeholder="<spring:message code="signup.placeholder.state" />">
						<div id="stateError" class="error"></div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="country"><spring:message code="signup.label.country" /></label> <input type="text"
							disabled="disabled" class="form-control" id="country"
							name="country" placeholder="<spring:message code="signup.placeholder.country" />">
						<div id="countryError" class="error"></div>
					</div>
					<div class="form-group col-md-6">
						<label for="email"><spring:message code="signup.label.email" /></label> <input type="text"
							class="form-control" id="email" name="email" placeholder="<spring:message code="signup.placeholder.email" />">
						<div id="emailError" class="error"></div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-4 control-label"><spring:message code="signup.label.gender" /></label>
						<div class="col-sm-12">
							<label class="radio-inline"><input type="radio"
								name="gender" id="gender" value="M" checked><spring:message code="signup.gender.radio.button.male" /></label> <label
								class="radio-inline ml-3"><input type="radio"
								name="gender" id="gender" value="F"><spring:message code="signup.gender.radio.button.female" /></label>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="phone"><spring:message code="signup.label.phone.number" /></label> <input type="number"
							class="form-control" id="phone" name="phone"
							placeholder="<spring:message code="signup.placeholder.phone.number" />">
						<div id="phoneError" class="error"></div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="username"><spring:message code="signup.label.username" /></label> <input type="text"
							class="form-control" id="username" name="username"
							placeholder="<spring:message code="signup.placeholder.username" />">
						<div id="usernameError" class="error"></div>
					</div>
					<div class="form-group col-md-6">
						<label for="password"><spring:message code="signup.label.password" /></label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="<spring:message code="signup.placeholder.password" />">
						<div id="passwordError" class="error"></div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<button type="button" id="backLogin"
							class="col-md-10 btn btn-danger"><spring:message code="signup.button.label.back" /></button>
					</div>
					<div class="form-group col-md-6">
						<button type="submit" class="col-md-10 btn btn-primary"
							id="addCustomer"><spring:message code="signup.button.label.signin" /></button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript"
		src="<c:url value="/static/js/login.js" />"></script>
</body>
</html>