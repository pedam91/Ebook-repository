<%@include file="header.jsp" %>

	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<div class="alert alert-danger">
			Login failed.<br />
			Cause: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<div id="container">
		<form class='form-signin' name='f' action="${loginUrl}" method='POST'>
			<div>Please enter your e-mail address and password:</div><br/>
			<input class="form-control" type="email" name='email' value='' placeholder="E-mail address" required autofocus><br/>
			<input class="form-control" type="password" name='password' placeholder="Password" required><br/>
			<input class="btn btn-lg btn-primary btn-block" type="submit" value="Login">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>

<%@include file="footer.jsp" %>
