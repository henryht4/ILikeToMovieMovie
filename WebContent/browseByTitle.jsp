<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="helper.DBConnection"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>I Like To Movie Movie</title>
</head>

<%@include file="navbar.jsp"%>


<link rel="stylesheet" href="style.css">

<body>
	<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
		
		<h1>Browse By Title</h1>
 				<% Connection connection = DBConnection.getConnection();
 				String query = "SELECT distinct LEFT(title,1) from movies ORDER BY LEFT(title,1)";
 				PreparedStatement pst=connection.prepareStatement(query);
 				ResultSet result = pst.executeQuery();
				while(result.next()){
				
				%>
				
					<a href="<%=request.getContextPath()%>/BrowseByTitle?letter=<%=result.getString(1) %>"> <%=result.getString(1) %> 
					</a></br>
				<%} %>
			
		
		<%
	
		
		if(request.getParameter("found")!=null){
			out.write("<h2 style=\"color:orange\">No Result Found</h2>");
		}
	%>
	</body>
</html>