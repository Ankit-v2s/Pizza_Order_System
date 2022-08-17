<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="customer.home.title" /></title>
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
			<%@include file="customernavbar.jsp"%>
			<div class="main-body">
				<div class="main-container">
					<div id="itemAddedSuccess" class="form-group row col-md-8" style="display: none;">
						<div class="alert alert-success">
							<strong><em class="fa fa-thumbs-up"></em> </strong> <span>Item Added Successfully</span>
						</div>
					</div>
					<div class="col-md-8 ">
						<div class="form-group row col-md-8">
							<select class="textbox" name="pizzaName" id="pizzaName" required="required">
								<option selected disabled value="">Select Pizza Name</option>
								<c:forEach items="${pizzalist}" var="pizza">
									<option value="${pizza.pizzaName}">${pizza.pizzaName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group row col-md-8">
							<input type="text" class="textbox" id="quantity" placeholder="Quantity">
						</div>
						<div class="form-group row col-md-8">
							<button type="button" class="btn btn-primary btn-block" id="addItem">Add item</button>
							<button type="button" class="btn btn-primary btn-block" id="pizzaOrder" style="display:none"
								data-toggle='modal' data-target='#paymentModal'>Order Now</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal" id="paymentModal" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Payment</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row m-auto p-4 mt-2">
						<div class="col-md-12 ">
							<div class="form-group ">
								<label for="amount">Total Amount</label> <input type="text"
									disabled="disabled" class="form-control" id="amount">
							</div>
							<div class="form-group ">
								<label for="coupon">Coupons</label> <select class="form-control"
									name="coupon" id="coupon" onmouseup="applyCoupons()">
									<option selected disabled value="">Select Coupon</option>
									<c:forEach items="${couponlist}" var="coupon">
										<option value="${coupon.couponCode}">${coupon.couponCode}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group ">
								<label for="totalAmount">Amount to be paid</label> <input type="text"
									disabled="disabled" class="form-control" id="totalAmount">
							</div>
							<div class="form-group ">
								<label for="mode">Mode Of Payment</label> <select class="form-control"
									name="mode" id="mode">
									<option selected disabled value="">Select Payment Mode</option>
									<c:forEach items="${paymentmodelist}" var="paymentMode">
										<option value="${paymentMode.mode}">${paymentMode.mode}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="pay">Pay Now</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/static/js/login.js" />"></script>
</body>
</html>