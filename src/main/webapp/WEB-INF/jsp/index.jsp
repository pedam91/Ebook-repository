<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home page</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<body>

	<div id="navbar" class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="https://github.com/spring-projects/spring-boot"> Spring Boot
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="./"> Home </a></li>
			</ul>
		</div>
	</div>
	<div class="jumbotron">
		<h1>Welcome to Ebooks repository home page!</h1>
		<p>
		<span id="status"></span>
		<br/>
		<h3> Listing : </h3> <br/>
	
		<ul>
			<c:forEach var="listValue" items="${lists}">
				<li>${listValue}</li>
			</c:forEach>
		</ul></p>
		<p>
	       <a class="btn btn-lg btn-primary" href="#navbar" role="button">Go &raquo;</a>
	    </p>
	</div>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>
