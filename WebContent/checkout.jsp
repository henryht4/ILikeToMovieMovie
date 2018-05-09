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

<title>Check Out</title>
</head>
<body>
	
	<div class="container">
			<center><h><font color="#00000">Check Out</font></h></center>
			<center><h3>Please fill out the information below to complete your purchase!</h3></center>
			
		<form action="CheckOut" method="post">
			<input type="text" name="firstName" class="form-control" placeholder="First Name" required/><br> 
			<input type="text" name="lastName" class="form-control" placeholder="Last Name" required/><br> 
			<input type="text" name="creditCard" class="form-control" placeholder="Credit Card #" required /><br> 
			<input type="text" name="expDate" class="form-control" placeholder="Expiration Date" required /><br> 
            <button class="btn btn-lg btn-default" type="submit"> Submit </button>   
		</form>			
			<c:if test="${not empty emptyCart}">
				<c:out value="${emptyCart}"/>
			</c:if>
			<c:if test="${not empty purchaseFail}">
				<c:out value="${purchaseFail}"/>
			</c:if>

	</div>


</body>
</html>