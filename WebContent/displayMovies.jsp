<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<table border>
		<tr>
			<td>ID</td>
			<td>Title</td>
			<td>Year</td>
			<td>Director</td>
			<td>List of Genres</td>
			<td>List of Stars</td>
			<td>Rating</td>
			<td>Price</td>
			<td>Buy</td>
		</tr>
		<c:forEach var="movie" items="${movies}">
			<tr><td>${movie.id }</td>
				<td>${movie.title }</td>
				<td>${movie.year }</td>
				<td>${movie.director }</td>
				<td>
				<c:forEach var="genre" items="${movie.genres}">
					${genre}
				</c:forEach>
				</td>
				<td>
				<c:forEach var="star" items="${movie.stars}">
				${star.name},  
				</c:forEach>
				</td>
				<td>${movie.rating }</td>
				<td>Price: $2</td>
				<td><a href="cart?title=${movie.title}&id=${movie.id}" class="btn btn-primary">Add to cart</a></td>
				</tr>							
			</c:forEach>
</table>
</html>