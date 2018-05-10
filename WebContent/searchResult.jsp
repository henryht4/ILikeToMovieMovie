<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="helper.MovieListing"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>

<link rel="stylesheet" href="style.css">
<%@include file="navbar.jsp" %>

<body>
		<div class="container" align ="center">
		<center>
		<h1>Search Results:</h1>
		<table border="1px" width="600px" color=#f17676>
		<tr>
			<th>ID</th>
			<th><a href="sortByTitle.jsp">Title</a></th>
			<th>Year</th>
			<th>Director</th>
			<th>Star Name</th>
			<th><a href="sortByRating.jsp">Ratings</a></th>
		</tr>
		
	<% ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies");
			for(MovieListing movie: movies){ %>
			<tr align="center">
				<td><%=movie.getId() %></td>
				<td><a href="#"><%=movie.getTitle() %></a></td>
				<td><%=movie.getYear() %></td>
				<td><%=movie.getDirector() %></td>
				<td><a href="#"><%=movie.getStarName() %></a></td>
				<td><%=movie.getRating() %></td>
			</tr>
	<% 		
			}
				%>
	</table></center>
	</div>
</body>
</html>