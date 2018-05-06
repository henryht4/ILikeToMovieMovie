package helper;

public class SingleStar {
	
	
	private int id;
	private String firstName;
	private String lastName;

	
	public SingleStar(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	//get ID of the star
	public int getId() {
		return id;
	}
	//get the star's first name
	public String  getFirstName() {
		return firstName;
	}
	//get the star's last name
	public String getLastName() {
		return lastName;
	}
	
}