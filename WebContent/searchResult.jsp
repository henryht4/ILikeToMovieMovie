<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="helper.MovieListing"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <link href="style.css" rel="stylesheet" type="text/css">
     	<%@include file="navbar.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="style.css">
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Search Results</title>
</head>
<body>
<div class="container">

			<h1>Search Results:</h1>
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
				<tr align ="center">
					<td><%=movie.getId() %></td>
					
					<td><a href=<%= "\"singleMovie.jsp?title=" + movie.getTitle() + "\"" %> ><%= movie.getTitle() %></a></td>
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
				</tr>
		<% 		
				}
		%>
		</table>
	</div>
</body>
</html>