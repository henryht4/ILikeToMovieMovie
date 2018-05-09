<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">     
				<tbody>
					<c:forEach var="movie" items="${movies}">
						<tr>
							<div class="media">
								<div class="media-body">
									<ul style="list-style-type:none">
										<li>ID: ${m.id}
										<li>Title: <a href="hyperlinkedmovie?id=${movie.id}">${movie.title}</a></li>
										<li>Year: ${movie.year}</li>
										<li>Director: ${movie.director}</li>
										<li class="horizontal">Genres:</li>
										<c:forEach var="genre" items="${movie.genres}">
											<li class="horizontal"><a
												href="browseresults?genre=${genre}">${genre}</a></li>
											<li class="horizontal">,</li>
										</c:forEach>
										<br>
										<li class="horizontal">Stars:</li>
										<c:forEach var="star" items="${movie.stars}">
											<li class="horizontal"><a
												href="hyperlinkedstar?id=${star.id}">${star.firstName}
													${star.lastName}</a></li>
											<li class="horizontal">,</li>
										</c:forEach>
										<li>Price: $5</li>
										<li><a href="cart?title=${movie.title}&id=${movie.id}" class="btn btn-primary">Add to cart</a></li>
						
									</ul>
								</div>
							</div>
						</tr>
					</c:forEach>
				</tbody>
			</table>
        </div>
    </div>
</div>