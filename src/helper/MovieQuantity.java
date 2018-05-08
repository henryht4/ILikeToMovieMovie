package helper;
//this class will be used to add and subtract the quantity of a movie item
public class MovieQuantity {

	private GrabMovie movie;
	private int quantity;
	
	public MovieQuantity(GrabMovie movie, int quantity) 
	{	
		this.movie = movie;
		this.quantity = quantity;
	}

	//gets the current movie
	public GrabMovie getMovie() 
	{
		return movie;
	}
	
	//gets the quantity of the current movie
	public int getQuantity() 
	{
		return quantity;
	}
	
	//sets the quantity of the current movie
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//adds the quantity of the current movie
	public void addQuantity() {
		this.quantity += 1;
	}
	
	//subtracts the quantity of the current movie
	public void subQuantity() {
		this.quantity -= 1;
	}
	
}
