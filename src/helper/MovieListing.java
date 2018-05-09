package helper;
//This class fetches the id and title of a given movie
public class MovieListing 
{
	private String id;
	private String title;

	
	public MovieListing(String movieId, String title) {
		super();
		this.id = movieId;
		this.title = title;
	
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	
}