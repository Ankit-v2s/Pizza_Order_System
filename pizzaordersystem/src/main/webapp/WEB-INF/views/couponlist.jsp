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
			<%@include file="employeenavbar.jsp"%>

			<!-- Modal -->
			<div class="modal" id="couponAddEdit" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Add/Edit
								Coupon</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="row m-auto p-4 mt-2">
								<div class="col-md-12 ">
									<input type="hidden" id="couponId">
									<div class="form-group ">
										<label for="couponcode">Coupon Code</label> <input type="text"
											class="form-control" id="couponcode"
											placeholder="Enter Coupon Name">
									</div>
									<div class="form-group ">
										<label for="discount">Discount</label> <input type="number"
											class="form-control" id="discount"
											placeholder="Enter Discount">
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" id="addEditCoupon"
								data-dismiss="modal">Save changes</button>
						</div>
					</div>
				</div>
			</div>
			<div class="main-body">
				<div class="main-body table-wrapper-scroll-y my-custom-scrollbar">
				<button type="button" class="btn btn-primary mb-2"
					data-toggle="modal" onclick="addCoupon()"
					data-target="#couponAddEdit">Add</button>
				<table class="table table-hover table-light">
					<tr>
						<td><strong>Coupon Id</strong></td>
						<td><strong>Coupon Code</strong></td>
						<td><strong>Discount</strong></td>
						<td><strong>Edit</strong></td>
						<td><strong>Delete</strong></td>
					</tr>
					<c:forEach items="${couponlist}" var="couponlist">
						<tr>
							<td>${couponlist.couponId}</td>
							<td>${couponlist.couponCode}</td>
							<td>${couponlist.discount}</td>
							<td><button type='button' class='btn btn-info'
									onclick="editCouponData('${couponlist.couponId}')"
									data-toggle='modal' data-target='#couponAddEdit'>
									<em class='fa fa-edit'></em>
								</button></td>
							<td><button type='button' class='btn btn-info'
									onclick="deleteCouponData('${couponlist.couponId}')"
									data-toggle='modal' data-target='#deleteCoupon'>
									<em class='fa fa-trash'></em>
								</button></td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="deleteCoupon" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					Confirm Delete
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Are you sure you wanna delete this ?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok" id="confirmDeleteCoupon"
						data-dismiss="modal">Delete</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/static/js/login.js" />"></script>
</body>
</html>