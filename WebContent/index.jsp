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

<script>
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
</head>

<%@include file="navbar.jsp"%>


<link rel="stylesheet" href="style.css">

<body>

<%
ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies");
request.getSession().setAttribute("movies", movies); %>
	<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
	
	    <p>Welcome to our movie database!</p><br>
    
    <center>
    <h2>Please Select an Option To Begin</h2>
    <form action="NormalSearch" method="post">
    <div class="row">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search" id="txtSearch" name="title" value="" onkeypress="return onlyAlphabets(event,this);"/>
          <button class="btn btn-lg btn-default btn-block login1" name ="search" type="submit">
            <span class="glyphicon glyphicon-search"></span>
          </button>
    </div>
  </div>
  </form></center>
    
    <button onclick="window.location.href='browse.jsp'">Browse Movies</button>
    
    <button onclick="window.location.href='search.jsp'">Search For Movies</button>
	
   
	</div>
    

    
    <script type="text/javascript">
    $(document).ready(function() {
    	var cache = {};
    $("#txtSearch").autocomplete({
        
        minLength: 3,
        autoFocus: true,
        
        highlight: true,
        source: function(request, response) {
        	
        	var term = request.term;
        	if ( term in cache ) {
                response(cache[term]);
                console.log(cache[term]);
                response( $.map( cache[term], function( item ) {
                	
                    return {
                        label: item.title,
                        value: item.title,
                    }
                    }));
              }
        	
            $.ajax({
                url: "AdvanceSearch",
                
                data: request,
                success: function( data, textStatus, jqXHR) {
                	
                    
                   
                    var items = JSON.parse(data);
                    console.log(items);
                    response( $.map( items.slice(0,10), function( item ) {
                    	cache[term] = items.slice(0,10);
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