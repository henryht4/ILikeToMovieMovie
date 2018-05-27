<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	    <p>Welcome to our movie database!</p><br>
    
    
    <h2>Browse Movies</h2>
    
    <button onclick="window.location.href='browseByGenre.jsp'">Browse By Genre</button>
    
    <button onclick="window.location.href='browseByTitle.jsp'">Browse By Title</button>
	
   
	</div>
	</body>
</html>