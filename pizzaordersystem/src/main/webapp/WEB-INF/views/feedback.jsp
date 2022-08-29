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
			<%@include file="customernavbar.jsp"%>
			<div class="main-body">
				<div class="main-container">
					<div class="col-md-8 ">
					<div id="addSuccess" class="form-group row col-md-8" style="display: none;">
						<div class="alert alert-success">
							<strong><em class="fa fa-thumbs-up"></em> </strong> <span><spring:message code="customer.feedback.successfully.added" /></span>
						</div>
					</div>
						<div class="form-group row col-md-8">
							<select class="textbox" id="feedbackStatus" name="feedbackStatus"
								required="required">
								<option selected disabled value=""><spring:message code="customer.feedback.type.disabled.option" /></option>
								<c:forEach items="${feedbacklist}" var="feedback">
									<option value="${feedback.feedbackStatusType}">${feedback.feedbackStatusType}</option>
								</c:forEach>
							</select>
							<div id="feedbackStatusError" class="error"></div>
						</div>
						<div class="form-group row col-md-8">
							<textarea id="comments" class="textbox" placeholder="<spring:message code="customer.feedback.comments.placeholder" />"></textarea>
							<div id="commentsError" class="error"></div>
						</div>
						<div class="form-group row col-md-8">
							<button type="button" id="addfeedback"
								class="btn btn-primary btn-block"><spring:message code="customer.feedback.button.label.add" /></button>
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
	<script type="text/javascript"
		src="<c:url value="/static/js/feedback.js" />"></script>
</body>
</html>