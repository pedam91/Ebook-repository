<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
				<a class="navbar-brand" href="<c:url value="/"/>">Home page</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="<c:url value="/search"/>">Search books</a></li>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="<c:url value="/book/create"/>">Create book</a></li>
						<li><a href="<c:url value="/category/create"/>">Create category</a></li>
						<li><a href="<c:url value="/user/all"/>">List all users</a></li>
					</sec:authorize>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="isAnonymous()">
						<li><a href="<c:url value="/register"/>">Register</a></li>
						<li><a href="<c:url value="/login"/>"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<sec:authentication var="loggedInUser" property="principal" />
						<li><div class="navbar-text">Signed in as: ${loggedInUser.username}</div></li>
						<li><a href="<c:url value="/user/${loggedInUser.id}/edit"/>">
							<span class="glyphicon glyphicon-user"></span> Edit user info</a></li>
						<li><a href="<c:url value="/logout"/>">
							<span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<c:if test="${infoMessage != null}"><div class="alert alert-info">${infoMessage}</div></c:if>
	<c:if test="${successMessage != null}"><div class="alert alert-success">${successMessage}</div></c:if>
	<c:if test="${errorMessage != null}"><div class="alert alert-danger">${errorMessage}</div></c:if>
