<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="header.jsp" %>

	<span id="status"></span>

	<table class="table table-hover">
		<thead>
			<tr>
				<td colspan="2" align="center">User info</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Name</td>
				<td>${user.name}</td>
			</tr>
			<tr>
				<td>E-mail address</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>Password</td>
				<td>${user.password}</td>
			</tr>
			<tr>
				<td>Type</td>
				<td>${user.type}</td>
			</tr>
			<tr>
				<td>Category</td>
				<td>${user.category.name}</td>
			</tr>
		</tbody>
	</table>

<%@include file="footer.jsp" %>