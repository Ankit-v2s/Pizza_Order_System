<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="error.page.title" /></title>
<link href="https://fonts.googleapis.com/css?family=Roboto:700" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/static/css/Error.css" />" />
</head>
<body>
<body>
	<div class="container">
		<h1><spring:message code="error.page.header" /></h1>
		<h2><spring:message code="error.page.sub.header" /></h2>
		<p>
			<spring:message code="error.page.message" /><br>
			<spring:message code="error.page.hyperlink.message" /> <a href="/pizzaordersystem/"><spring:message code="error.page.hyperlink" /></a>
		</p>
	</div>
</body>
</html>