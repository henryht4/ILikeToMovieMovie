<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Browse Movies</title>
</head>

<%@include file="navbar.jsp"%>


<link rel="stylesheet" href="style.css">

<body>
<div class="div1" >
    <h1>Browsing</h1>
    <center>
    
    <p>Search for Movies or Browse by Genre</p><br>
    
  	<button type="button" class="btn btn-default" onclick="window.location.href='browsebytitle.jsp'">Browse By Title</button>
	
	<button type="button" class="btn btn-default" onclick="window.location.href='browsegenreform.jsp'">Browse By Genre</button>
	</center>
    
</div>
</body>

</html>