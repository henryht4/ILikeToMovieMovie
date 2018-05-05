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
  

  <body>

	<div class="div1" >
    <h1 ALIGN = "CENTER">Search by Movie Title Letter</h1>
  	<center>
  	<form ACTION = "browsetitleresults" METHOD = "POST">
  		<h5> Select A Letter:</h5>
  		<select name = "letter">
  		 <option value="A%"> A</option>
  		 <option value="B%"> B</option>
 		 <option value="C%"> C</option>
 		 <option value="D%"> D</option>
 		 <option value="E%"> E</option>
 		 <option value="F%"> F</option>
 		 <option value="G%"> G</option>
 		 <option value="H%"> H</option>
 		 <option value="I%"> I</option>
 		 <option value="J%"> J</option>
 		 <option value="K%"> K</option>
 		 <option value="L%"> L</option>
 		 <option value="M%"> M</option>
 		 <option value="N%"> N</option>
 		 <option value="O%"> O </option>
 		 <option value="P%"> P</option>
 		 <option value="Q%"> Q</option>
 		 <option value="R%"> R</option>
 		 <option value="S%"> S</option>
 		 <option value="T%"> Y</option>
 		 <option value="U%"> U</option>
 		 <option value="V%"> V</option>
 		 <option value="W%"> W</option>
 		 <option value="X%"> X</option>
 		 <option value="Y%"> Y</option>
 		 <option value="Z%"> Z</option>
 		 </select>
 		 <br>
 		 Select # of Movies to Display Per Page
 		 <input type = "radio" name = "limit" value = "5" checked>5
 		 <input type = "radio" name = "limit" value = "10"/>10
 		 <input type = "radio" name = "limit" value = "15"/>15
 		 <br><br>
 		 <input type="SUBMIT" value = "Search">
 		 
  	</form>
 	</center>
  	</div>
  </body>
</html>