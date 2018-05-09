package helper;
//this class will be used to add and subtract the quantity of a movie item
public class MovieQuantity {

<<<<<<< HEAD
	private MovieListing movie;
	private int quantity;
	
	public MovieQuantity(MovieListing movie, int quantity) 
=======
	private GrabMovie movie;
	private int quantity;
	
	public MovieQuantity(GrabMovie movie, int quantity) 
>>>>>>> changedName
	{	
		this.movie = movie;
		this.quantity = quantity;
	}

	//gets the current movie
<<<<<<< HEAD
	public MovieListing getMovie() 
=======
	public GrabMovie getMovie() 
>>>>>>> changedName
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
