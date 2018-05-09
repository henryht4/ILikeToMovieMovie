<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Single Movie Data</title>
</head>
<%@ include file="navbar.jsp"%>
<body>
<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<div class="media">
				<div class="media-body" class="center">
					<ul style="list-style-type:none">
						<li>ID: ${movie.id}
						<li>Title: ${movie.title}</li>
						<li>Year: ${movie.year}</li>
						<li>Director: ${movie.director}</li>
						<li class="horizontal">Genres:</li>
						<c:forEach var="genre" items="${movie.genres}">
							<li class="horizontal"><a href="browseresults?genre=${genre}">${genre}</a></li>
							<li class="horizontal">,</li>
						</c:forEach>
						<br>
						<li class="horizontal">Stars:</li>
						<c:forEach var="star" items="${movie.stars}">
							<li class="horizontal"><a href="hyperLinkedStars?id=${star.id}">${star.name}</a></li>
							<li class="horizontal">,</li>
						</c:forEach>
						<li>Price: $1</li>

						<li><a href="cart?title=${movie.title}&id=${movie.id}" class="btn btn-primary">Add to cart</a></li>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>