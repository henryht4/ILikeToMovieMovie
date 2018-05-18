<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="helper.MovieListing"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<%@include file="navbar.jsp"%>
<body>


<%
			
			ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies"); 			

			for (int i = 0; i < movies.size()-1; i++)
			{
			   int max = i;
			   for (int j = i+1; j < movies.size(); j++)
			         if (movies.get(j).getRating()>movies.get(max).getRating()) 
			        	 max = j;
			   MovieListing temp = movies.get(i);
			   movies.set(i,movies.get(max));
			   movies.set(max,temp) ;
			}
			request.getSession().setAttribute("Movies", movies);
			response.sendRedirect("searchResult.jsp");

	%>
			

</body>
</html>