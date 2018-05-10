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
<body>


<%
//swap function to order by title
System.out.print(request.getParameter("num"));
			ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies"); 			

			for (int i = 0; i < movies.size()-1; i++)
			{
			   int min = i;
			   for (int j = i+1; j < movies.size(); j++)
			         if (movies.get(j).getTitle().compareTo(movies.get(min).getTitle()) <= -1){
			        	 min = j;
			         }
			   MovieListing temp = movies.get(i);
			   movies.set(i,movies.get(min));
			   movies.set(min,temp) ;
			}
			request.getSession().setAttribute("Movies", movies);
			response.sendRedirect("searchResult.jsp");

	%>
			

</body>
</html>