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
			<%@include file="customernavbar.jsp"%>
			<div id="itemAddedSuccess" class="form-group row col-md-12"
				style="display: none;">
				<div class="alert alert-success">
					<strong><em class="fa fa-thumbs-up"></em> </strong><span><spring:message
							code="customer.home.alert.item.added" /></span>
				</div>
			</div>
			<div id="paymentSuccess" class="form-group row col-md-12"
				style="display: none;">
				<div class="alert alert-success">
					<strong><em class="fa fa-thumbs-up"></em> </strong> <span><spring:message
							code="customer.home.alert.payment.added" /></span>
				</div>
			</div>
			<div class="main-body">
				<div class="main-container">
					<div class="col-md-8 ">
						<div class="form-group row col-md-8">
							<select class="textbox" name="pizzaName" id="pizzaName"
								required="required">
								<option selected disabled value=""><spring:message
										code="customer.home.pizzaname.disabled.option" /></option>
								<c:forEach items="${pizzalist}" var="pizza">
									<option value="${pizza.pizzaName}">${pizza.pizzaName}</option>
								</c:forEach>
							</select>
							<div id="pizzanameError" class="error"></div>
						</div>
						<div class="form-group row col-md-8">
							<input type="text" class="textbox" id="quantity"
								placeholder="<spring:message code="customer.home.placeholder.quantity" />">
							<div id="quantityError" class="error"></div>
						</div>
						<div class="form-group row col-md-8">
							<button type="button" class="btn btn-primary btn-block"
								id="addItem">
								<spring:message code="customer.home.button.label.add.item" />
							</button>
							<button type="button" class="btn btn-primary btn-block" style="display: none;"
								id="pizzaOrder" data-toggle='modal' data-target='#paymentModal'>
								<spring:message code="customer.home.button.label.order.now" />
							</button>
						</div>
					</div>
				</div>
			</div>

			<div class="table-wrapper-scroll-y my-custom-scrollbar"
				style="height: 275px;">
				<table class="table table-hover table-light" id="cart-table" style="display: none;">
					<tr>
						<td><strong>Pizza Name</strong></td>
						<td><strong>Quantity</strong></td>
					</tr>
					<tbody id="cart">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal" id="paymentModal" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<spring:message code="customer.home.payment.modal.header" />
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row m-auto p-4 mt-2">
						<div class="col-md-12 ">
							<div class="form-group ">
								<label for="amount"><spring:message
										code="customer.home.payment.modal.label.amount" /></label> <input
									type="text" disabled="disabled" class="form-control"
									id="amount">
							</div>
							<div class="form-group ">
								<label for="coupon"><spring:message
										code="customer.home.payment.modal.label.coupon" /></label> <select
									class="form-control" name="coupon" id="coupon"
									onmouseup="applyCoupons()">
									<option selected value=""><spring:message
											code="customer.home.payment.modal.coupon.disabled.option" /></option>
									<c:forEach items="${couponlist}" var="coupon">
										<option value="${coupon.couponCode}">${coupon.couponCode}</option>
									</c:forEach>
								</select>
								<div id="couponError" class="error"></div>
							</div>
							<div class="form-group ">
								<label for="totalAmount"><spring:message
										code="customer.home.payment.modal.label.amount.paid" /></label> <input
									type="text" disabled="disabled" class="form-control"
									id="totalAmount">
							</div>
							<div class="form-group ">
								<label for="mode"><spring:message
										code="customer.home.payment.modal.label.payment.mode" /></label> <select
									class="form-control" name="mode" id="mode">
									<option selected disabled value=""><spring:message
											code="customer.home.payment.modal.payment.mode.disabled.option" /></option>
									<c:forEach items="${paymentmodelist}" var="paymentMode">
										<option value="${paymentMode.mode}">${paymentMode.mode}</option>
									</c:forEach>
								</select>
								<div id="paymentmodeError" class="error"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">
						<spring:message
							code="customer.home.payment.modal.button.label.close" />
					</button>
					<button type="button" class="btn btn-primary" id="pay">
						<spring:message
							code="customer.home.payment.modal.button.label.pay" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/static/js/login.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/static/js/validation.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/static/js/order-pizza.js" />"></script>
</body>
</html>