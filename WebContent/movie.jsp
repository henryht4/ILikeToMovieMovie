<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="helper.MovieListing"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link href="movie.css" rel="stylesheet" type="text/css">
    <link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Single Movie</title>
</head>
<%@include file="navbar.jsp"%>
<center>
<body>
<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
		<h1>Movie Details</h1>
		<center>
		<table border="1px" width="600px">
		<tr style="font-size: large; font-weight: bold">
			<th>ID</th>
			<th>Title</th>
			<th>Year</th>
			<th>Director</th>
			<th>Star Name</th>
			<th>Genres</th>
			<th>Ratings</th>
		</tr>
		
	<%
			ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies");
	for(MovieListing movie:movies){
		System.out.println(movie.getTitle());
	}
			String title= (String)request.getParameter("title");
			
			for(MovieListing movie:movies){
				if(movie.getTitle().equalsIgnoreCase(title))
				{
	%>
			<tr>
				<td><%=movie.getId() %></td>
				
				<td><%= movie.getTitle() %></td>
				<td><%=movie.getYear() %></td>
				<td><%=movie.getDirector() %></td>
			 	<td> <ul>
			 	<% for(int i=0; i < movie.getStarName().size();i++)
			 	{%>
			 	
			 		<li><a href="<%=request.getContextPath()%>/StarServlet?id=<%=movie.getStarID().get(i)  %>">
			 	<%=movie.getStarName().get(i)+"\n" %></a></li>
			 <%	}%>
			 	</ul></td>
			 	<td><ul>
			 	<% for(int i=0; i < movie.getGenres().size();i++)
			 	{%>
			 	<li><a href="<%=request.getContextPath()%>/GenreServlet?id=<%=movie.getGenreID().get(i)  %>">
			 		<%=movie.getGenres().get(i)+"\n" %></a> </li>
			 <%	}%>
			 	</td></ul>
			 	
				<td><%=movie.getRating() %></td> 
				<%request.getSession().setAttribute("movies", movies); %>
			<form action= "AddToCart" method="post">
				<td><input type="hidden" name="movieId" value="<%=movie.getId()%>"> 
				<button class="btn btn-lg btn-default btn-block login1" id="txtuser" type="submit"> Add To Cart </button>   </td>
				</form>
			</tr>
	<% 		}
			}
			
			
	%>
	</table>
	</center>
	</div>
</body>
</center>
</html>