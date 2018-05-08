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
import helper.SingleMovie;
import helper.MovieQuantity;


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
		
			
			//arraylist of movies from MovieQuantity
			ArrayList<MovieQuantity> items = (ArrayList<MovieQuantity>) session.getAttribute("items");

			//if movieId does exist, then we parse the string
			int id = 0;
			if(movieId != null)
				id = Integer.parseInt(movieId);
			//initialize the arraylist
			if(items == null)
				items = new ArrayList<MovieQuantity>();
			
			//checks for any update for item already existing in cart
			if(update != null) {
				for(MovieQuantity i : items) 
				{
					String q = request.getParameter(Integer.toString(i.getMovie().getId()));
					int newQuantity = 0;
					if(q != null)
						newQuantity = Integer.parseInt(q);
						i.setQuantity(newQuantity);
				}
			}
			
			if(title != null & id != 0) {
				SingleMovie movie = new SingleMovie(id, title);
				
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
						MovieQuantity item = new MovieQuantity(movie, 1);
						items.add(item);
					}
					//if movie object already exists in arraylist
					else {
						items.get(index).addQuantity();
					}
				}
				//if the arraylist is empty
				else {
					MovieQuantity item = new MovieQuantity(movie, 1);
					items.add(item);
				}
				
			}
			
			//if delete gets called
			if (delete != null) {
				int deleteId = Integer.parseInt(delete);
				int index = -1;
				
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i).getMovie().getId() == deleteId)
							index = deleteId;
					}
				
				//remove the item from the arraylist
				if (index != -1) {
				items.remove(index);
				}
			}
			
			//delete all items in the arraylist
			if (deleteAll != null) {
				items.clear();
			}
			
			String empty = "";
			if(items.isEmpty())
				empty = "Empty Cart";
			
			int total = 0;
			for(MovieQuantity i : items){
				total += i.getQuantity()*5;
			}
			
			
			session.setAttribute("emptyCart", empty);
			session.setAttribute("items", items);
			session.setAttribute("total", total);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	}