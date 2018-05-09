package helper;

import java.util.ArrayList;

//imports the arraylist from cart.java and returns that list when needed
public class ItemsInCart {

	private ArrayList<CartItem> itemsList;
	private Boolean clearCart = false;

	public ItemsInCart() 
	{	
	}

	public ArrayList<CartItem> getItemsList(){
		return itemsList;
	}
	
	public void setItemsList(ArrayList<CartItem> oldItemsList) {
		itemsList = new ArrayList<CartItem>(oldItemsList);
	}
	
	//trigger a cart clear whenever a purchase is made successfully
	public void emptyCart() {
		clearCart = true;
	}
	
	//when a cart gets cleared, make sure to reset the clearCart status
	public void resetCartStatus() {
		clearCart = false;
	}
	
	public Boolean getCartStatus() {
		return clearCart;
	}
	
	//empties a cart
	public ArrayList<CartItem> clearCart(ArrayList<CartItem> itemsList) {
		itemsList.clear();
		return itemsList;
	}
}
