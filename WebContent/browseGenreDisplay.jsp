<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Genre Results</title>
</head>

<%@ include file="navbar.jsp"%>

<body>

<div>
 <ul class="center">
 	<li class="horizontal">Sort: </li>
 	<li class="horizontal"><a href="browseresults?sort=title&genre=${genre}">By Title</a></li>
 	<li class="horizontal"><a href="browseresults?sort=rating&genre=${genre}">By Rating</a></li>
 </ul>
</div>

<div>
 <ul class="center">
 	<li class="horizontal">Number of displayed items: </li>
 	<li class="horizontal"><a href="browseresults?limit=10&genre=${genre}">10</a></li>
 	<li class="horizontal"><a href="browseresults?limit=25&genre=${genre}">25</a></li>
 	<li class="horizontal"><a href="browseresults?limit=50&genre=${genre}">50</a></li>
 	<li class="horizontal"><a href="browseresults?limit=100&genre=${genre}">100</a></li>
 </ul>
</div>

<%@ include file="displayMovies.jsp"%>


<div style="text-align: center">
	<nav aria-label="pagination">
		<ul class="pagination">
			<li><a href="browseresults?paging=previous&req=genre&genre=${genre}" aria-label="Previous"> <spanaria-hidden="true">&laquo;</span></a></li>
			<li class="active"><span>${page} <span class="sr-only">(current)</span></span>
			</li>
			<li><a href="browseresults?paging=next&req=genre&genre=${genre}" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</nav>
</div>

</body>
</html>