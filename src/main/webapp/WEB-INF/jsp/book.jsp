<%@include file="header.jsp"%>

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
			<td>
				<c:forEach items="${book.files}" var="file">
					<c:out value="${file.fileName} "></c:out>
				</c:forEach>				
			</td>
		</tr>
	</tbody>
</table>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value="/book/${book.id}/edit"/>">Edit</a>
	<br />
	<a href="<c:url value="/book/${book.id}/delete"/>">Delete</a>
</sec:authorize>

<%@include file="footer.jsp"%>
