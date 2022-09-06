<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/static/css/pizzaorder.css" />" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<%@include file="employeenavbar.jsp"%>
			<div id="problemCoupon" class="col-sm-12" style="display: none;">
				<div class="alert alert-danger">
					<strong><em class="fa fa-thumbs-down"></em> </strong> 
					<span><spring:message code="pizza.order.system.error.message" /></span>
				</div>
			</div>
			<!-- Modal -->
			<div class="modal" id="couponAddEdit" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"><spring:message code="employee.coupon.add.edit.modal.heading" /></h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="row m-auto p-4 mt-2">
								<div class="col-md-12 ">
									<input type="hidden" id="couponId">
									<div class="form-group ">
										<label for="couponcode"><spring:message code="employee.coupon.add.edit.modal.coupon.code.label" /></label> 
										<input type="text" class="form-control" id="couponcode" placeholder="<spring:message code="employee.coupon.add.edit.modal.coupon.code.placeholder" />">
										<div id="couponcodeError" class="error"></div>
									</div>
									<div class="form-group ">
										<label for="discount"><spring:message code="employee.coupon.add.edit.modal.discount.label" /></label> 
										<input type="number" class="form-control" id="discount" placeholder="<spring:message code="employee.coupon.add.edit.modal.discount.placeholder" />">
										<div id="discountError" class="error"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="employee.coupon.add.edit.modal.button.close.label" /></button>
							<button type="button" class="btn btn-primary" id="addEditCoupon"><spring:message code="employee.coupon.add.edit.modal.button.save.label" /></button>
						</div>
					</div>
				</div>
			</div>
			<div class="main-body">
				<div class="main-body table-wrapper-scroll-y my-custom-scrollbar">
					<button type="button" class="btn btn-primary mb-2" data-toggle="modal" onclick="addCoupon()"
					data-target="#couponAddEdit"><spring:message code="employee.coupon.add.button.label" /></button>
				<table class="table table-hover table-light">
					<tr>
						<td><strong><spring:message code="employee.coupon.table.header.id" /></strong></td>
						<td><strong><spring:message code="employee.coupon.table.header.coupon.code" /></strong></td>
						<td><strong><spring:message code="employee.coupon.table.header.discount" /></strong></td>
						<td><strong><spring:message code="employee.coupon.table.header.edit" /></strong></td>
						<td><strong><spring:message code="employee.coupon.table.header.delete" /></strong></td>
					</tr>
					<c:forEach items="${couponlist}" var="couponlist">
						<tr>
							<td>${couponlist.couponId}</td>
							<td>${couponlist.couponCode}</td>
							<td>${couponlist.discount}</td>
							<td>
								<button type='button' class='btn btn-info' onclick="editCouponData('${couponlist.couponId}')" data-toggle='modal' data-target='#couponAddEdit'>
									<em class='fa fa-edit'></em>
								</button>
							</td>
							<td>
								<button type='button' class='btn btn-info' onclick="deleteCouponData('${couponlist.couponId}')" data-toggle='modal' data-target='#deleteCoupon'>
									<em class='fa fa-trash'></em>
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="deleteCoupon" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<spring:message code="employee.coupon.delete.modal.heading" />
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body"><spring:message code="employee.coupon.delete.modal.message" /></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="employee.coupon.delete.modal.button.cancel.label" /></button>
					<a class="btn btn-danger btn-ok" id="confirmDeleteCoupon" data-dismiss="modal"><spring:message code="employee.coupon.delete.modal.button.delete.label" /></a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/static/js/login.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/validation.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/coupon.js" />"></script>
</body>
</html>