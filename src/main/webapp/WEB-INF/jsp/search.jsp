<%@include file="header.jsp"%>

<form action="<c:url value="/search"/>" accept-charset="UTF-8" method="post">

	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	<table class="table table-hover">
		<tr>
			<th>Attribute</th>
			<th>Search value</th>
			<th>Search condition</th>
		</tr>
		<tr>
			<td>Title</td>
			<td><input type="text" name="title" /></td>
			<td>
				<select name="titlest" >
					<c:forEach items="${searchTypes}" var="st">
						<option value="${st}">
							<c:out value="${st}"></c:out>
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="titlesc">
					<c:forEach items="${occures}" var="o">
						<option value="${o}">${o}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Author</td>
			<td><input type="text" name="author" /></td>
			<td>
				<select name="authorst" >
					<c:forEach items="${searchTypes}" var="st">
						<option value="${st}">
							<c:out value="${st}"></c:out>
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="authorsc">
					<c:forEach items="${occures}" var="o">
						<option value="${o}">${o}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Keywords</td>
			<td><input type="text" name="kw" /></td>
			<td>
				<select name="kwst" >
					<c:forEach items="${searchTypes}" var="st">
						<option value="${st}">
							<c:out value="${st}"></c:out>
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="kwsc">
					<c:forEach items="${occures}" var="o">
						<option value="${o}">${o}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Content</td>
			<td><input type="text" name="content" /></td>
			<td>
				<select name="contentst" >
					<c:forEach items="${searchTypes}" var="st">
						<option value="${st}">
							<c:out value="${st}"></c:out>
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="contentsc">
					<c:forEach items="${occures}" var="o">
						<option value="${o}">${o}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Language</td>
			<td><input type="text" name="language" /></td>
			<td>
				<select name="languagest" >
					<c:forEach items="${searchTypes}" var="st">
						<option value="${st}">
							<c:out value="${st}"></c:out>
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="languagesc">
					<c:forEach items="${occures}" var="o">
						<option value="${o}">${o}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input type="submit" value="Search" />
			</td>
		</tr>
	</table>
</form>

<%@include file="footer.jsp"%>

