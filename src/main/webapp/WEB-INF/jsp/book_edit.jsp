<%@include file="header.jsp"%>

<sf:form name="bookForm" acceptCharset="UTF-8" method="post" modelAttribute="book">

	<table class="table table-hover">
		<thead>
			<tr>
				<th colspan="2">Book info</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Title</td>
				<td><sf:input path="title" value="${book.title}"/></td>
				<td><sf:errors path="title"/></td>
			</tr>
			<tr>
				<td>Author</td>
				<td><sf:input path="author" value="${book.author}"/></td>
				<td><sf:errors path="author"/></td>
			</tr>
			<tr>
				<td>Keywords</td>
				<td><sf:input path="keywords" value="${book.keywords}"/></td>
				<td><sf:errors path="keywords"/></td>
			</tr>
			<tr>
				<td>Publication year</td>
				<td><sf:input path="publicationYear" value="${book.publicationYear}"/></td>
				<td><sf:errors path="publicationYear"/></td>
			</tr>
			<tr>
				<td>Language</td>
				<td>
					<sf:select path="language">
					     <sf:option value="">&nbsp;</sf:option>
					     <sf:options itemLabel="name" items="${languages}"/>
					</sf:select>
				</td>
				<td><sf:errors path="language"/></td>
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
			<c:if test="${book.cataloguer != null}">
			<tr>
				<td>Cataloguer</td>
				<td>
					<sf:select path="cataloguer">
					     <sf:option value="${book.cataloguer}">${book.cataloguer.name}</sf:option>
					</sf:select>
				</td>
				<td><sf:errors path="cataloguer"/></td>
			</tr>
			</c:if>
			<!-- 
			<tr>
				<td>File</td>
				<td>${book.files}</td>
				<td><sf:input path="files" value="${book.files}"/></td>
				<td><sf:errors path="files"/></td>
			</tr>
			-->
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Submit" ></td>
			</tr>
		</tbody>
	</table>

</sf:form>

<%@include file="footer.jsp"%>

