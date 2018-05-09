<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>


<ul style="list-style-type:none">
	<li>ID: ${id}
	<li>Title: ${title}</a></li>
	<li>Year: ${year}</li>
	<li>Director: ${director}</li>
	<li>Genres: ${genres}</li>
	<li>Stars:${stars}</li>
	<li>Price: $5</li>
	<li><a href="cart?title=${movie.title}&id=${movie.id}" class="btn btn-primary">Add to cart</a></li>
						
	</ul>
</html>