<%@include file="header.jsp"%>

<h1>Search results</h1>

<table class="table table-hover">
	<c:forEach items="${searchResults}" var="res">
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td width="20%">Title</td>
			<td><a href="<c:url value="/book/${res.id}"/>">${res.title}</a></td>
			<td><a href="<c:url value="/search/similar/${res.id}"/>">More like this</a></td>
		</tr>
	</c:forEach>
</table>

<%@include file="footer.jsp"%>
