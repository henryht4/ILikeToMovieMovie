package helper;
//This class fetches the id and title of a given movie
public class GrabMovie 
{
	private int id;
	private String title;

	
	public GrabMovie(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
}