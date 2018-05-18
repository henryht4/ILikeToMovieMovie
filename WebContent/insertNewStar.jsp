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
    
    
    <h2>Insert New Star</h2>
    
    
    
  <form action="insertStar" method="post">
      <!--name-->
     <div id="second"> <h4>Name:</h4><br></div>
      <div id="align"><input type="text" name="name" value="" required /></div>
  <br>
      <!--birthyear-->
      <div id="third"><h4>Birth Year:</h4><br></div>
      <div id="align"><input type="text" name="year"   /></div>
  <br>
      
      <button class="btn btn-lg btn-default btn-block login1" id="txtuser" type="submit" name ="search"> Insert </button>   
</form> 


   
	</div>
    

    
    
</body>

</html>