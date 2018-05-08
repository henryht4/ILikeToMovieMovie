package helper;

public class StarListing {
	
	
	private String id;
	private String name;

	
	public StarListing(String id, String name) {
		this.id = id;
		this.name = name;
		
	}
	
	//get ID of the star
	public String getId() {
		return id;
	}
	//get the star's first name
	public String  getName() {
		return name;
	}
}