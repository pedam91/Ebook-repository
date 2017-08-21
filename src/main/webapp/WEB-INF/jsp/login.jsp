<%@include file="header.jsp"%>

<style>
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>

<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	<div class="alert alert-danger">
		Login failed.<br /> Cause:
		${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	</div>
</c:if>

<div id="container" class="container">
	<form class='form-signin' name='loginForm' action="${loginUrl}" method='POST'>
		<h2 class="form-signin-heading">Please sign in:</h2>
		<br /> 
		<input class="form-control" type="email" name='email' placeholder="E-mail address" required autofocus>
		<br />
		<input class="form-control" type="password" name='password' placeholder="Password" required>
		<br />
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</div>

<%@include file="footer.jsp"%>
