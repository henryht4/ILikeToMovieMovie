<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="style.css">
   
<%@include file="navbar.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>
<body>	
	<center><h-login>Login</h-login></center>
	

	<center><div class="container">
	<form action= "login" method="post">
		<center>
		<table>
			<p>Please sign in to access the site!</p>
			<p></p>
			<tr><td><input type="email" name="email" placeholder="Email Address" required> </td></tr>
			<tr><td><p></p></td></tr>
			<tr><td> <input type="password" name= "password" placeholder ="Password" required></td></tr>
			<tr><td>
			<p></p>
			
			
            <button class="btn btn-lg btn-default btn-block login1" id="txtuser" type="submit"><span class="glyphicon glyphicon-log-in"></span> Login </button>   
			
		</table>
		</center>

			<c:if test="${not empty errorMessage}">
				<c:out value="${errorMessage}"/>
			</c:if>
		<center><div class="g-recaptcha" data-sitekey="6LeIc1kUAAAAAFxLdQJxIjculbMJmC3swTKLbEbi"></div><br></center>
			
	</form>
	</div>
	
	</center>
</body>
</html>