<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>Ebooks repository</title>
	<link rel="stylesheet" href="/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/bootstrap-theme.min.css" />
</head>
<body>

	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Home page</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="<c:url value="/search"/>">Search books</a></li>
					<sec:authorize access="isAnonymous()">
						<li><a href="<c:url value="/login"/>">Login</a></li>
						<li><a href="<c:url value="/register"/>">Register</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a href="<c:url value="/logout"/>">Logout</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
	<br/><br/><br/>