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
    
    
    <h2>Insert New Movie</h2>
    
    
    
  <form action="insertMovie" method="post">
      <!--title-->
     <div id="second"> <h4>Title:</h4><br></div>
      <div id="align"><input type="text" name="title" value="" required /></div>
  <br>
      <!--year-->
      <div id="third"><h4>Year:</h4><br></div>
      <div id="align"><input type="text" name="year"  /></div>
  <br>
  
   <!--director-->
      <div id="third"><h4>Director:</h4><br></div>
      <div id="align"><input type="text" name="director" /></div>
  <br>
  
   <!--star-->
      <div id="third"><h4>Star:</h4><br></div>
      <div id="align"><input type="text" name="star"  required /></div>
  <br>
      
      
   <!--genre-->
      <div id="third"><h4>Genre</h4><br></div>
      <div id="align"><input type="text" name="genre" required  /></div>
  <br>
      
      <button class="btn btn-lg btn-default btn-block login1" id="txtuser" type="submit" name ="search"> Insert </button>   
</form> 


   <%
	
		
		if(request.getParameter("found")!=null){
			String msg=request.getParameter("found");
			out.write("<h2 style=\"color:orange\">"+msg+"</h2>");
		}
	%>
	</div>
    

    
    
</body>

</html>