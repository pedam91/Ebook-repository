<%@include file="header.jsp" %>

	<sec:authorize access="isAnonymous()">
		<div>Welcome to the visitor home page. For additional functionalities, please log in.</div>
	</sec:authorize>

	<br/>
	<h3>Categories of books:</h3> <br/>

	<ul>
		<c:forEach var="category" items="${categories}">
			<li><a href="<c:url value="/category/${category.id}"/>">${category.name}</a></li>

			<ul>
				<c:forEach var="book" items="${category.books}"> 
					<li><a href="<c:url value="/book/${book.id}"/>">${book.title}</a></li>
				</c:forEach>
			</ul>

		</c:forEach>
	</ul>

<%@include file="footer.jsp" %>
