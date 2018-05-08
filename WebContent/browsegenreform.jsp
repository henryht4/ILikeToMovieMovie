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
    <title>Search Movies By Title Letter</title>
  </head>
  
<%@include file="navbar.jsp"%>


<link rel="stylesheet" href="style.css">
  
</head>
  
  <body>
  	<div class="div1" >
  	<h1 ALIGN = "CENTER">Browse by Movie Genre</h1>
  	<center>
  	<form ACTION = "browsegenreresults" METHOD = "POST">
  		<h5> Select A Genre:</h5>
  		<select name = "genre">
  		 <option value="Action"> Action</option>
  		 <option value="Adult"> Adult<br></option>
 		 <option value="Adventure"> Adventure<br></option>
 		 <option value="Animation"> Animation<br></option>
 		 <option value="Biography"> Biography<br></option>
 		 <option value="Comedy"> Comedy <br></option>
 		 <option value="Crime"> Crime<br></option>
 		 <option value="Crime"> Documentary <br></option>
 		 <option value="Drama"> Drama<br></option>
 		 <option value="Family"> Family<br></option>
 		 <option value="Fantasy"> Fantasy<br></option>
 		 <option value="History"> History<br></option>
 		 <option value="Horror"> Horror<br></option>
 		 <option value="Music"> Music<br></option>
 		 <option value="Musical"> Musical <br></option>
 		 <option value="Mystery"> Mystery<br></option>
 		 <option value="Reality-TV"> Reality-TV <br></option>
 		 <option value="Romance"> Romance<br></option>
 		 <option value="Sci-Fi"> Sci-Fi<br></option>
 		 <option value="Sport"> Sport<br></option>
 		 <option value="Thriller"> Thriller<br></option>
 		 <option value="War"> War<br></option>
 		 <option value="Western"> Western<br></option>
 		 </select>
 		 <br>

 		 <input type="SUBMIT" value = "Search">
 		 
 		 
  	</form>
  	</center>
  	</div>
  </body>
</html>