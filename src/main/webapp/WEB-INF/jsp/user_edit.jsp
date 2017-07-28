<%@include file="header.jsp" %>

<sf:form name="editUser" acceptCharset="UTF-8" method="post" modelAttribute="user" action="/user/${user.id}">
	<table class="table table-hover">
		<thead>
			<tr>
				<td colspan="2" align="center">User info</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Name</td>
				<td><sf:input path="name" value="${user.name}"/></td>
				<td><sf:errors path="name"/></td>
			</tr>
			<tr>
				<td>E-mail address</td>
				<td><sf:input type="email" path="email" value="${user.email}"/></td>
				<td><sf:errors path="email"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><sf:password path="password" value="${user.password}"/></td>
				<td><sf:errors path="password"/></td>
			</tr>
			<tr>
				<td>User type</td>
				<td>
					<sf:select path="type" itemValue="${user.type}">
						<sf:option value="ROLE_ADMIN"/>
						<sf:option value="ROLE_USER"/>
					</sf:select>
				</td>
				<td><sf:errors path="type"/></td>
			</tr>
			<tr>
				<td>Category</td>
				<td>
					<sf:select path="category">
					     <sf:option value="">&nbsp;</sf:option>
					     <sf:options itemLabel="name" items="${categories}"/>
					</sf:select>
				</td>
				<td><sf:errors path="category"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Submit" ></td>
			</tr>
		</tbody>
	</table>
</sf:form>

<%@include file="footer.jsp" %>
