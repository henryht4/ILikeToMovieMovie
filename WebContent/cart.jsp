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
			<h2>${emptyCart}</h2>
			
			<form action="cart?update="true" method="post">
				<table>
					<%--iterate through each "item" in the arraylist of movies in cart and display those movies --%>
					<c:forEach var="item" items="${items}">
						<tr>
						<td>
							<div>
								<a href="singlemovie?id=${item.getMovie().getId()}">${item.getMovie().getTitle()}</a>
							</div>
						</td>
							<td>Product: </td>
							<td>Quantity: </td>
							<td><input name="${item.getMovie().getId()}" class="form-control" value="${item.getQuantity()}">
							</td>
							<td>Price: $10</td>
							<td><a class="btn btn-default" href="cart?remove=${item.getMovie().getId()}">Remove</a>
						</tr>
					</c:forEach>
					<tr>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td><h3>Total: </h3></td>
					<td class="text-right"><h3>
					<strong>$${total}</strong>
						</h3></td>
					</tr>
						
					<%-- Buttons/Submissions --%>
					<tr class="spacing">
						<td><button type="submit" class="btn btn-default"> Update Cart </button></td>
						<td><a class="btn btn-default" style="background-color:red; color: #ffffff" href="cart?removeAll=true">
									Remove All
						</a></td>
						<td><a class="btn btn-default" style="background-color:#1eff9f" href="checkout.jsp">
								Proceed to Checkout
						</a></td>
					</tr>
				</table>
			</form>
	</div>
</body>
</html>