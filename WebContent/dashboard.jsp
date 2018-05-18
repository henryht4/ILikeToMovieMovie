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
    
    
    <h2>Employee Dashboard</h2>
    <%
	
		
		if(request.getParameter("found")!=null){
			out.write("<h2 >Star Inserted</h2>");
		}
	%>
    
   <button onclick="window.location.href='insertNewStar.jsp'">Insert New Star</button>
   <button onclick="window.location.href='insertNewMovie.jsp'">Insert New Movie</button>
    <button onclick="window.location.href='Metadata.jsp'">See Metadata</button>
	
	
   
	</div>
	</body>
</html>