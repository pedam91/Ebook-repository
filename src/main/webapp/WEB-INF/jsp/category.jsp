<%@include file="header.jsp"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th colspan="2" align="center">Category info</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Name</td>
			<td>${category.name}</td>
		</tr>
	</tbody>
</table>

<table class="table table-hover">
	<thead>
		<tr>
			<th colspan="2">Books:</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="book" items="${category.books}"> 
			<tr>
				<td>
					<a href="<c:url value="/book/${book.id}"/>">${book.title}</a>
				<td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value="/category/${category.id}/edit"/>">Edit</a>
	<br />
	<a href="<c:url value="/category/${category.id}/delete"/>">Delete</a>
</sec:authorize>

<%@include file="footer.jsp"%>
