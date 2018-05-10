<!DOCTYPE html>
<html>
    <link href="style.css" rel="stylesheet" type="text/css">
     	<%@include file="navbar.jsp" %>
    
      <script language="Javascript" type="text/javascript">
      
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
          
          
          <!--function for only alphabets-->
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

 

    </script></head>
	<div class="container">

	<center><h1>Search Movies</h1></center>
 	<center><h2>Please input what you want to search for in the respective fields</h2></center>
 
    
    <!--search area-->
    
  <form action="SearchServlet" method="post" >
      <!--title-->
      <div id="align"><input type="text" name="title" value="" placeholder="Title" onkeypress="return onlyAlphabets(event,this);" /></div>
  <br>
      <!--year-->
      <div id="align"><input type="text" name="year" value="" placeholder="Year" onkeypress="return onlyNos(event,this)" /></div>
  <br>
      <!--director-->
      <div id="align"><input type="text" name="director" value="" placeholder="Director" onkeypress="return onlyAlphabets(event,this);" /></div>
  <br>
      <!--star name-->
      <div id="align"><input type="text" name="starName" value="" placeholder="Star Name" onkeypress="return onlyAlphabets(event,this); " /></div>
  <br><br>
       <button class="btn btn-lg btn-default" type="submit" value="Search" name="search"> Submit Search </button>   
</form> 

<h2 style="color:orange">${errorMessage}</h2>

	<% if(request.getParameter("found") != null ){
			out.write("<h2 style=\"color:orange\">No Result Found</h2>");
		}
	%>

	</div>
 
</body>
</html>