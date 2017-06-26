<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="header.jsp" %>

	<span id="status"></span>

	<table class="table table-hover">
		<thead>
			<tr>
				<td colspan="2" align="center">Book info</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Title</td>
				<td>${book.title}</td>
			</tr>
			<tr>
				<td>Author</td>
				<td>${book.author}</td>
			</tr>
			<tr>
				<td>Keywords</td>
				<td>${book.keywords}</td>
			</tr>
			<tr>
				<td>Publication year</td>
				<td>${book.publicationYear}</td>
			</tr>
			<tr>
				<td>Language</td>
				<td>${book.language.name}</td>
			</tr>
			<tr>
				<td>Category</td>
				<td>${book.category.name}</td>
			</tr>
			<tr>
				<td>Cataloguer</td>
				<td>${book.cataloguer.name}</td>
			</tr>
			<tr>
				<td>Download file</td>
				<td>${book.files}</td>
			</tr>
		</tbody>
	</table>

<%@include file="footer.jsp" %>