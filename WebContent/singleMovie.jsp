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
<center>
<body>
		<h1>Movie Details</h1>
		<table border="1px" width="600px">
		<tr style="font-size: large; font-weight: bold" align="center">
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
			String title= (String)request.getParameter("title");
			
			for(MovieListing movie:movies){
				if(movie.getTitle().equalsIgnoreCase(title))
				{
	%>
			<tr align="center">
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
			</tr>
	<% 		}
			}
					
	%>
	</table>
</body>
</center>
</html>