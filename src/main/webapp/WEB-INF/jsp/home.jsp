<%@include file="header.jsp" %>

	<sec:authorize access="isAnonymous()">
		<div>Welcome to the visitor home page. For additional functionalities, please log in.</div>
	</sec:authorize>

	<span id="status"></span>
	<br/>
	<h3>Categories of books:</h3> <br/>

	<ul>
		<c:forEach var="category" items="${categories}">
			<li>${category.name}</li>

			<ol>
				<c:forEach var="book" items="${category.books}"> 
					<li><a href="${pageContext.request.contextPath}/book/${book.id}"><c:out value="${book.title}"/></a></li>
				</c:forEach>
			</ol>

		</c:forEach>
	</ul>

<%@include file="footer.jsp" %>