<%@include file="header.jsp"%>

<table class="table table-hover">

	<thead>
		<tr>
			<th colspan="4" align="center">List of all users:</th>
		</tr>
		<tr>
			<th>Name</th>
			<th>E-mail address</th>
			<th>Type</th>
			<th>Category</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="user" items="${allUsers}">
			<tr>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>${user.type}</td>
				<td>${user.category.name}</td>
				<td><a href="<c:url value="/user/${user.id}/edit"/>">Edit</a></td>
				<td><a href="<c:url value="/user/${user.id}/delete"/>">Delete</a></td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<%@include file="footer.jsp"%>
