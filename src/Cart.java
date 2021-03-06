import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

//import helper classes to use helper functions 
import helper.MovieListing;
import helper.CartItem;
import helper.ItemsInCart;

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	
	public Cart() {} //public constructor
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//keeps everything in 1 session
			HttpSession session = request.getSession(true);
		
	
			String title = request.getParameter("title");
			String movieId = request.getParameter("id");
			String delete = request.getParameter("delete");
			String deleteAll = request.getParameter("deleteAll");
			String update = request.getParameter("update");
		
			
			//arraylist of movies from CartItem
			ArrayList<CartItem> items = (ArrayList<CartItem>) session.getAttribute("items");
		/*	ItemsInCart updateCart = new ItemsInCart();

			//if a purchase has been made, clear the cart
			if(updateCart.getCartStatus() == true)
			{
				items.clear();
				updateCart.resetCartStatus(); //resetCartStatus to false
			}

			//if movieId does exist, then we delete the item
			String id = "";
			if(movieId != null)
				id = deleteItem(id);
			//initialize the arraylist
			if(items == null)
				items = new ArrayList<CartItem>();*/
			
			//checks for any update for item already existing in cart
			if(update != null) {
				for(CartItem i : items) 
				{
					String q = request.getParameter(i.getMovie().getId());
					int newQuantity = 0;
					
					if(q != null && i.getQuantity()!=Integer.parseInt(q))
						
						newQuantity = Integer.parseInt(q);
						i.setQuantity(newQuantity);
				}
			}
			
		/*	if(title != null & id != "") {
				MovieListing movie = new MovieListing();
				
				//if the arraylist is not empty
				if(!items.isEmpty()) {
					int index = -1; //initialize index as -1
					
					for (int i = 0; i < items.size(); i++) {
						if (items.get(i).getMovie().getId() == id)
								index = i;
						else
							index = -1;
						}
					
					//if the movie object is not in the arraylist, add new movie object with quantity 1
					if (index == -1)
					{
						CartItem item = new CartItem(movie, 1);
						items.add(item);
					}
					//if movie object already exists in arraylist
					else {
						items.get(index).addQuantity();
					}
				}
				//if the arraylist is empty
				else {
					CartItem item = new CartItem(movie, 1);
					items.add(item);
				}
				
			}
			*/
			//if delete gets called
			if (delete != null) {
				String deleteId = deleteItem(delete);
				int index = -1;
				
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i).getMovie().getId().equals(movieId))
							index = i;
					}
				
				//remove the item from the arraylist
				if (index != -1) {
				items.remove(index);
				
				}
				
				
			//	session.setAttribute("emptyCart", empty);
				
			}
			
			//delete all items in the arraylist
			else if (deleteAll != null) {
				items.clear();
			}
			
			String empty = "";
			if(items.isEmpty())
				empty = "Empty Cart";
			
			int total = 0;
			for(CartItem i : items){
				total += i.getQuantity()*10;
			}
			
			//update ItemsInCart to be used for checkout
//			//updateCart.setItemsList(items);
			
			session.setAttribute("emptyCart", empty);
			session.setAttribute("items", items);
			session.setAttribute("total", total);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
			
		}
	
	private String deleteItem(String id) {
		return null;
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	}