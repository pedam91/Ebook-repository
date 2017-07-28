<%@include file="header.jsp"%>

<table class="table table-hover">
	<thead>
		<tr>
			<td colspan="2" align="center">Category info</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Name</td>
			<td>${category.name}</td>
		</tr>
	</tbody>
</table>

<a href="<c:url value="/category/${category.id}/edit"/>">Edit</a>
<br />
<a href="<c:url value="/category/${category.id}/delete"/>">Delete</a>

<%@include file="footer.jsp"%>
