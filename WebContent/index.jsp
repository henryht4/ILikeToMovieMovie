<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="helper.MovieListing"%>
    <%@page import="java.util.ArrayList"%>
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

<%
ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies");
request.getSession().setAttribute("movies", movies); %>
	<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
	
	    <p>Welcome to our movie database!</p><br>
    
    
    <h2>Please Select an Option To Begin</h2>
    <div class="row">
    <div class="col-md-4 col-md-push-4">
    <input type="text" id="txtSearch" class="form-control" placeholder="Search">
    </div>
    </div>
    
    <button onclick="window.location.href='browse.jsp'">Browse Movies</button>
    
    <button onclick="window.location.href='search.jsp'">Search For Movies</button>
	
   
	</div>
    

    
    <script type="text/javascript">
    $(document).ready(function() {
    $("#txtSearch").autocomplete({
        
        minLength: 3,
        autoFocus: true,
        
        highlight: true,
        source: function(request, response) {
            $.ajax({
                url: "AdvanceSearch",
                
                data: request,
                success: function( data, textStatus, jqXHR) {
                	
                    
                   
                    var items = JSON.parse(data);
                    console.log(items);
                    response( $.map( items.slice(0,10), function( item ) {
                        return {
                            label: item.title,
                            value: item.title,
                        }
                        }));
                },
                error: function(jqXHR, textStatus, errorThrown){
                	console.log(jqXHR)
                     console.log( textStatus);
                }
            });
        },
        select: function(event, ui) {
        	event.preventDefault();
        	$('#txtSearch').val(ui.item.label);
        	window.location.href = encodeURI("singleMovie.jsp?title="+ui.item.label);
        	
        }
 
    });
});
    
</script>
</body>

</html>