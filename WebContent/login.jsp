<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="style.css">

<%@include file="navbar.jsp"%>

<body>	
	<center><h-login>Login</h-login></center>
	
	<center><div class="signin">
	<form action= "login" method="post">
		<table>
			<p></p>
			<p></p>
			<tr><td colspan ="3"><input type="email" name="email" placeholder="Email Address" required> </td></tr>
			<tr><td><p></p></td></tr>
			<tr><td> <input type="password" name= "password" placeholder ="Password" required></td></tr>
			<tr><td colspan="3">
			<p></p>
            <button class="btn btn-lg btn-default btn-block login1" id="txtuser" type="submit"><span class="glyphicon glyphicon-log-in"></span> Login </button>   
			
		</table>
		
			<c:if test="${not empty errorMessage}">
				<c:out value="${errorMessage}"/>
			</c:if>
			
	</form>
	</div>
	</center>
</body>
</html>