<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Ebooks repository home page</title>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/app.js"></script>
	<link rel="stylesheet" href="css/app.css"></link>
</head>

<body>
	<h1>Welcome to Ebooks repository home page!</h1>

	<span id="status"></span>
	<br/>
	<h3> Listing : </h3> <br/>

	<ul>
		<c:forEach var="listValue" items="${lists}">
			<li>${listValue}</li>
		</c:forEach>
	</ul>

</body>
</html>