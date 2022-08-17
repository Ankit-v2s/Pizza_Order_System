<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" href="<c:url value="/static/css/pizzaorder.css" />" />
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
			<div class="col-md-6 jumbotron offset-md-3 " style="padding-top: 25px">
				<h3 class="text-center">Register Here</h3>
				<div class="form-group">
					<label for="name">Name</label> <input type="text"
						class="form-control" id="name" name="name" placeholder="Name">
				</div>
				<div class="form-group">
					<label for="inputAddress">Address Line 1</label> <input type="text"
						class="form-control" id="addressLine1" name="addressLine1"
						placeholder="Address 1">
				</div>
				<div class="form-group">
					<label for="inputAddress2">Address Line 2</label> <input
						type="text" class="form-control" id="addressLine2"
						name="addressLine2" placeholder="Address 2">
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="city">City</label>
							<select class="form-control" id="city" onchange="getCityDetails()" name="city" >
									<option selected disabled value="">Select City</option>
									<c:forEach items="${cityList}" var="city">
										<option value="${city.cityName}">${city.cityName}</option>
									</c:forEach>
								</select>
					</div>
					<div class="form-group col-md-6">
						<label for="state">State</label> <input type="text" disabled="disabled"
							class="form-control" id="state" name="state" placeholder="State">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="country">Country</label> <input type="text" disabled="disabled"
							class="form-control" id="country" name="country" placeholder="Country">
					</div>
					<div class="form-group col-md-6">
						<label for="email">Email</label> <input type="text"
							class="form-control" id="email" name="email" placeholder="Email">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
							<label class="col-sm-4 control-label">Gender</label>
							<div class="col-sm-12">
								<label class="radio-inline"><input type="radio"name="gender" id="gender" value="M" checked> Male</label> 
								<label class="radio-inline ml-3"><input type="radio" name="gender" id="gender" value="F"> Female</label> 
							</div>
						</div>
					<div class="form-group col-md-6">
						<label for="phone">Phone Number</label> <input type="text"
							class="form-control" id="phone" name="phone" placeholder="Phone Number">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="username">Username</label> <input type="text"
							class="form-control" id="username" name="username" placeholder="UserName">
					</div>
					<div class="form-group col-md-6">
						<label for="password">Password</label> <input type="password"
							class="form-control" id="password" name="password" placeholder="Password">
					</div>
				</div>
				
				<button type="button" id="backLogin" class="col-md-5 btn btn-danger">Back</button>
				<span class="col-md-2"></span>
				<button type="submit" class="col-md-5 btn btn-primary"
					id="addCustomer">Sign in</button>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<c:url value="/static/js/login.js" />"></script>
</body>
</html>