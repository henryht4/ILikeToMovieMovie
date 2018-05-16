<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="helper.MovieListing"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="style.css">
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<title>Search Results</title>
</head>
<%@include file="navbar.jsp"%>
<body>
<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
		<h1>Search Results:</h1>
		<center>
		<table border="1px" width="600px">
		<tr>
			<th>ID</th>
			<th>Title<br><a href="sortByTitleDESC.jsp"> v </a><a href="sortByTitleASC.jsp"> ^ </a></th>
			<th>Year</th>
			<th>Director</th>
			<th>Star Name</th>
			<th>Genres</th>
			<th>Ratings<br><a href="sortByRatingDESC.jsp"> v </a><a href="sortByRatingASC.jsp"> ^ </a></a></th>
			<th>Limit<br><a>25</a><a> 50</a><a> 100</a></th>
			<th><a>prev</a>/<a>next</a></th>
		</tr>
		
	<%
			
			ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies");
			for(MovieListing movie:movies){
				
	%>
			<tr>
				<td><%=movie.getId() %></td>
				<td><a href=<%= "/movie.jsp?title=" + movie.getTitle() + "/" %> ><%= movie.getTitle() %></a></td>
				<td><%=movie.getYear() %></td>
				<td><%=movie.getDirector() %></td>
			 	<td><ul>
			 	<% for(int i=0; i < movie.getStarName().size();i++)
			 	{	
			 	%>
			 	<li><a href="<%=request.getContextPath()%>/StarServlet?id=<%=movie.getStarID().get(i) %>">
			 	<%=movie.getStarName().get(i)+"\n" %></a></li>
			 		
			 <%	}%>
			 	</ul></td>
			 	<td><ul>
			 	<% for(int i=0; i < movie.getGenres().size();i++)
			 	{%>
			 	
			 		<li><%=movie.getGenres().get(i) +"\n"%></li>
			 <%	}%>
			 	</ul></td>
				<td><%=movie.getRating() %></td> 
				<%request.getSession().setAttribute("movies", movies); %>
			<form action= "AddToCart" method="post">
				<td><input type="hidden" name="movieId" value="<%=movie.getId()%>"> 
				<button class="btn btn-lg btn-default btn-block login1" id="txtuser" type="submit"> Add To Cart </button>   </td>
				</form>

			</tr>
	<% 		
			}
			
			
	%>
	</table>
	</center>
	</div>
</body>
</html>