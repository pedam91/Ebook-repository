<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<li><a href="<c:url value="/login"/>">Login</a></li>
					<li><a href="<c:url value="/register"/>">Register</a></li>
					<li><a href="<c:url value="/logout"/>">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
	<br/><br/><br/>