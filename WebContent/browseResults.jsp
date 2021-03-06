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

<title>Browse By Genre</title>
</head>
<%@include file="navbar.jsp"%>
<body>
<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
		<h1>Browse By Genre Results</h1>
		<table border="1px" width="600px">
		<tr>
			<th>ID</th>
			<th><a href="sortByTitle.jsp">Title</a></th>
			<th>Year</th>
			<th>Director</th>
			<th>Star Name</th>
			<th>Genres</th>
			<th><a href="sortByRating.jsp">Ratings</a></th>
		</tr>
		
	<%
			
			ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies");
			for(MovieListing movie:movies){
				
	%>
			<tr>
				<td><%=movie.getId() %></td>
				
				<td><a href=<%= "\"SingleMovie.jsp?title=" + movie.getTitle() + "\"" %> ><%= movie.getTitle() %></a></td>
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
	
	</div>
</body>
</html>