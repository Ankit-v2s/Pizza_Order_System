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
			<div class="main-body table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-hover table-light">
					<tr>
						<td><strong>Employee Id</strong></td>
						<td>${employee.employeeId}</td>
					</tr>
					<tr>
						<td><strong>Employee Name</strong></td>
						<td>${employee.employeeName}</td>
					</tr>
					<tr>
						<td><strong>Email</strong></td>
						<td>${employee.email}</td>
					</tr>
					<tr>
						<td><strong>Gender</strong></td>
						<td>${employee.gender}</td>
					</tr>
					<tr>
						<td><strong>Address Line 1</strong></td>
						<td>${employee.addressLine1}</td>
					</tr>
					<tr>
						<td><strong>Address Line 2</strong></td>
						<td>${employee.addressLine2}</td>
					</tr>
					<tr>
						<td><strong>City Name</strong></td>
						<td>${employee.cityName}</td>
					</tr>
					<tr>
						<td><strong>State Name</strong></td>
						<td>${employee.stateName}</td>
					</tr>
					<tr>
						<td><strong>Country Name</strong></td>
						<td>${employee.countryName}</td>
					</tr>
					<tr>
						<td><strong>Phone Number</strong></td>
						<td>${employee.phoneNumber}</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#employeeEdit">
								Edit <em class='fa fa-edit'></em>
							</button>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<div class="modal" id="employeeEdit" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Update
								Details</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<input type="hidden" id="employeeId"
								value="${employee.employeeId}">
							<div class="form-group row">
								<label for="email" class="col-sm-3 col-form-label">Email</label>
								<div class="col-sm-8">
									<input type="email" class="form-control" id="email"
										value="${employee.email}" name="email">
										<div id="emailError" class="error"></div>
								</div>
							</div>
							<div class="form-group row">
								<label for="addressLine1" class="col-sm-3 col-form-label">Address
									Line 1</label>
								<div class="col-sm-8">
									<textarea class="form-control" id="address1" name="address1">${employee.addressLine1}</textarea>
									<div id="address1Error" class="error"></div>
								</div>
							</div>
							<div class="form-group row">
								<label for="addressLine2" class="col-sm-3 col-form-label">Address
									Line 2</label>
								<div class="col-sm-8">
									<textarea class="form-control" id="address2" name="address2">${employee.addressLine2}</textarea>
									<div id="address2Error" class="error"></div>
								</div>
							</div>
							<div class="form-group row">
								<label for="city" class="col-sm-3 col-form-label">City</label>
								<div class="col-sm-8">
									<select
										class="form-control" id="city" onmouseup="getCityDetails()"
										name="city">
										<option selected value="${employee.cityName}">${employee.cityName}</option>
										<c:forEach items="${cityList}" var="city">
											<option value="${city.cityName}">${city.cityName}</option>
										</c:forEach>
									</select>
									<div id="cityError" class="error"></div>
								</div>
							</div>
							<div class="form-group row">
								<label for="state" class="col-sm-3 col-form-label">State</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="state" disabled="disabled"
										value="${employee.stateName}" name="state">
										<div id="stateError" class="error"></div>
								</div>
							</div>

							<div class="form-group row">
								<label for="country" class="col-sm-3 col-form-label">Country</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="country" disabled="disabled"
										value="${employee.countryName}" name="country">
										<div id="countryError" class="error"></div>
								</div>
							</div>
							<div class="form-group row">
								<label for="phoneNumber" class="col-sm-3 col-form-label">Phone
									Number</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="phoneNumber"
										value="${employee.phoneNumber}" name="phoneNumber">
										<div id="phoneError" class="error"></div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary"
								 id="updateEmployee">Submit</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/static/js/login.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/static/js/validation.js" />"></script>
</body>
</html>