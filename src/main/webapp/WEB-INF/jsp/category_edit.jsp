<%@include file="header.jsp"%>

<sf:form name="categoryForm" acceptCharset="UTF-8" method="post" modelAttribute="category">

	<table class="table table-hover">
		<thead>
			<tr>
				<th colspan="2">Category info</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Name</td>
				<td><sf:input path="name" value="${category.name}"/></td>
				<td><sf:errors path="name"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Submit" ></td>
			</tr>
		</tbody>
	</table>

</sf:form>

<%@include file="footer.jsp"%>

