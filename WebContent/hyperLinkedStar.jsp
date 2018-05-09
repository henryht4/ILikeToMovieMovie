<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Single Star Data</title>
</head>
<%@ include file="navbar.jsp"%>
<body>
<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<div class="media">
				<div class="media-body" class="center">
					<ul style="list-style-type:none">
						<li>ID: ${star.id}
						<li>Name: ${star.Name}</li>
						<li>birthYear: ${star.birthYear}</li>
						<li class="horizontal">Movies:</li>
						<c:forEach var="movie" items="${star.movies}">
							<li class="horizontal"><a href="hyperlinkedmovies?id=${movie.id}">${movie.title}</a></li>
							<li class="horizontal">,</li>
						</c:forEach>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>