<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="script.js"></script>

<link rel="stylesheet" href="style.css">
<%@include file="navbar.jsp" %>

<title>Shopping Cart</title>
</head>


<body>
	
	<div class="container">

	<h1>Shopping Cart</h1>

		<form type=POST action=carts.jsp>
		<BR>
		Please enter item to add or remove:
		<br>
		Add Item:
		
		<SELECT NAME="item">
		<OPTION>Beavis & Butt-head Video collection
		<OPTION>X-files movie
		<OPTION>Twin peaks tapes
		<OPTION>NIN CD
		<OPTION>JSP Book
		<OPTION>Concert tickets
		<OPTION>Love life
		<OPTION>Switch blade
		<OPTION>Rex, Rugs & Rock n' Roll
		</SELECT>
		
		
		<br> <br>
		<INPUT TYPE=submit name="submit" value="add">
		<INPUT TYPE=submit name="submit" value="remove">
		
		</form>
	</div>
</body>
</html>