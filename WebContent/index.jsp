<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
	
	    <p>Welcome to our movie database! This is the main page</p><br>
    
    
    <h2>Buttons to all webpages for QUICK ACCESS TESTING</h2>
    
    <button onclick="window.location.href='browse.jsp'">Browse Movies</button>
    
    <button onclick="window.location.href='searchform.html'">Search For Movies</button>
	
    
    <p><a href = "http://localhost:8080/ILikeToMovieMovie/ratings">Link to Ratings</a></p>
    <p><a href = "http://localhost:8080/ILikeToMovieMovie/searchform.html">Link to Search Form</a></p>
    <p><a href = "http://localhost:8080/ILikeToMovieMovie/titleresult?title=">Link to Empty Title Result</a></p>
    <p><a href = "http://localhost:8080/ILikeToMovieMovie/yearresult?year=">Link to Empty Year Result</a></p>
    <p><a href = "http://localhost:8080/ILikeToMovieMovie/directorresult?director=">Link to Empty Director Result</a></p>
    <p><a href = "http://localhost:8080/ILikeToMovieMovie/moviebystarresult?star=">Link to Empty Star Form</a></p>
    <p><a href = "http://localhost:8080/ILikeToMovieMovie/genreform.html">Link to Genre Search Form</a></p>
    <p><a href = "http://localhost:8080/ILikeToMovieMovie/genreresults?genre=">Link to Empty Genre Search Results</a></p>

   
	</div>
    

    
    
</body>

</html>