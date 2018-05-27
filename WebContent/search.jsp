<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<script src='https://www.google.com/recaptcha/api.js'></script>
<head>
<script>
<!--function for only numerics-->
        function onlyNos(e, t) {

            try {

                if (window.event) {

                    var charCode = window.event.keyCode;

                }

                else if (e) {

                    var charCode = e.which;

                }

                else { return true; }

                if (charCode > 31 && (charCode < 48 || charCode > 57)) {

                    return false;

                }

                return true;

            }

            catch (err) {

                alert(err.Description);

            }

        }
          
          
       
           function onlyAlphabets(e, t) {

            try {

                if (window.event) {

                    var charCode = window.event.keyCode;

                }

                else if (e) {

                    var charCode = e.which;

                }

                else { return true; }

                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode==32 )

                    return true;

                else

                    return false;

            }

            catch (err) {

                alert(err.Description);

            }

        }

 

    </script>
    
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>I Like To Movie Movie</title>

<script src='https://www.google.com/recaptcha/api.js'></script>

</head>

<%@include file="navbar.jsp"%>


<link rel="stylesheet" href="style.css">

<body>
	<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
	
	    <p>Welcome to our movie database!</p><br>
    
    
    <h2>SEARCH MOVIES</h2>
    
    
    <!--search area-->
    
  <form action="SearchServlet" method="post">
      <!--title-->
     <div id="second"> Title:</div>
      <div id="align"><input type="text" name="title" value="" onkeypress="return onlyAlphabets(event,this);" /></div>
  <br>
      <!--year-->
      <div id="third">Year:</div>
      <div id="align"><input type="text" name="year" value="" onkeypress="return onlyNos(event,this)" /></div>
  <br>
      <!--director-->
      <div id="fourth">Director:</div>
      <div id="align"><input type="text" name="director" value="" onkeypress="return onlyAlphabets(event,this);" /></div>
  <br>
      <!--star name-->
      <div id="fifth">Star name:</div>
      <div id="align"><input type="text" name="starName" value="" onkeypress="return onlyAlphabets(event,this); " /></div>
  <br>
  <br>
  
  <button class="btn btn-lg btn-default" id="txtuser" type="submit" name ="search"> Search </button>   
  <center><div class="g-recaptcha" data-sitekey="6LeIc1kUAAAAAFxLdQJxIjculbMJmC3swTKLbEbi"></div><br></center>
     
</form> 

<h2>${errorMessage}</h2>

	<%
	
		
		if(request.getParameter("found")!=null){
			out.write("<h2 style=\"color:orange\">No Result Found</h2>");
		}
	%>
	
   
	</div>
    

    
    
</body>

</html>