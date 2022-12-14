<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<div id="problemPizza" class="col-sm-12" style="display: none;">
				<div class="alert alert-danger">
					<strong><em class="fa fa-thumbs-down"></em> </strong> 
					<span><spring:message code="pizza.order.system.error.message" /></span>
				</div>
			</div>
			<!-- Modal -->
			<div class="modal" id="pizzaAddEdit" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"><spring:message code="employee.pizza.add.edit.modal.heading" /></h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="row m-auto p-4 mt-2">
								<div class="col-md-12 ">
									<input type="hidden" id="pizzaId">
									<div class="form-group ">
										<label for="pizzaname"><spring:message code="employee.pizza.add.edit.modal.pizza.name.label" /></label> 
										<input type="text" class="form-control" id="pizzaname" placeholder="<spring:message code="employee.pizza.add.edit.modal.pizza.name.placeholder" />">
										<div id="pizzanameError" class="error"></div>
									</div>
									<div class="form-group ">
										<label for="price"><spring:message code="employee.pizza.add.edit.modal.price.label" /></label> 
										<input type="number" class="form-control" id="price" placeholder="<spring:message code="employee.pizza.add.edit.modal.price.placeholder" />">
										<div id="priceError" class="error"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="employee.pizza.add.edit.modal.button.close.label" /></button>
							<button type="button" class="btn btn-primary" id="addEditPizza"><spring:message code="employee.pizza.add.edit.modal.button.save.label" /></button>
						</div>
					</div>
				</div>
			</div>

			<div class="main-body">
				<button type="button" class="btn btn-primary m-2" onclick="addPizza()" data-toggle="modal" data-target="#pizzaAddEdit">
					<spring:message code="employee.pizza.add.button.label" /> <em class='fa fa-plus'></em>
				</button>
				<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-hover table-light">
					<thead>
						<tr>
							<td><strong><spring:message code="employee.pizza.table.header.id" /></strong></td>
							<td><strong><spring:message code="employee.pizza.table.header.name" /></strong></td>
							<td><strong><spring:message code="employee.pizza.table.header.price" /></strong></td>
							<td><strong><spring:message code="employee.pizza.table.header.edit" /></strong></td>
							<td><strong><spring:message code="employee.pizza.table.header.delete" /></strong></td>
						</tr>
					</thead>
					<tbody id="pizzaTable">
						<c:forEach items="${pizzalist}" var="pizzalist">
							<tr>
								<td>${pizzalist.pizzaId}</td>
								<td>${pizzalist.pizzaName}</td>
								<td>${pizzalist.price}</td>
								<td>
									<button type='button' class='btn btn-info' id='editPizza' onclick="editPizzaData('${pizzalist.pizzaId}')" data-toggle='modal' data-target='#pizzaAddEdit'>
										<em class='fa fa-edit'></em>
									</button>
								</td>
								<td>
									<button type='button' class='btn btn-info' onclick="deletePizzaData('${pizzalist.pizzaId}')" data-toggle='modal' data-target='#deletePizza'>
										<em class='fa fa-trash'></em>
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="deletePizza" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<spring:message code="employee.pizza.delete.modal.heading" />
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body"><spring:message code="employee.pizza.delete.modal.message" /></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="employee.pizza.delete.modal.button.cancel.label" /></button>
					<a class="btn btn-danger btn-ok" id="confirmDeletePizza" data-dismiss="modal"><spring:message code="employee.pizza.delete.modal.button.delete.label" /></a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/static/js/login.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/validation.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/pizza.js" />"></script>
</body>
</html>