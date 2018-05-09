package helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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

//this class establishes all the sales action if customers information matches 
public class SaleActions {
	
	String loginUser = "mytestuser";
    String loginPasswd = "mypassword";
    String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
	
	public void addSale(ItemsInCart cart, CheckOutInfo info) throws SQLException
	{
		
		try {
			java.util.Date date = new java.util.Date();

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection(loginUrl,loginUser,loginPasswd);
		
		
			String query = "INSERT INTO sales (customerID, moviesID, saleDate) VALUES (?,?,?)";
			
			java.sql.PreparedStatement statement = connection.prepareStatement(query);
		
			//set the values within the statement
			statement.setObject(1, info.getCustomerId()); //add customerID
			
			ArrayList<CartItem> checkOutCart = cart.getItemsList();//add items to checkOutCart of sales action
		
			//iterate through the cart items and grab the movieIDs
		
			for(int i = 0; i < checkOutCart.size(); i++)
			{
				String moviesID;
			
				//get the moviesID from basket
				moviesID = checkOutCart.get(i).getMovie().getId();
			
				statement.setObject(2, moviesID); //add movieID
				statement.executeUpdate();
			}
			
			statement.setObject(3, new SimpleDateFormat("yyyy-mm-dd").format(date)); //add current date

			connection.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public boolean checkCustomerInfo(ItemsInCart cart, CheckOutInfo info) throws SQLException
	{
		boolean infoStatus = false;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection(loginUrl,loginUser,loginPasswd);
			
			String query = "SELECT COUNT(id) FROM creditcards WHERE id = ? AND firstName = ? "
				+ "AND lastName = ? AND expiration = ?";
			
			java.sql.PreparedStatement statement = connection.prepareStatement(query);

			//check credit card information
			statement.setObject(1, info.getCredCard());
			statement.setObject(2, info.getFirstName());
			statement.setObject(3, info.getLastName());
			statement.setObject(4, info.getExpDate());
		
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				infoStatus = true;
				addSale(cart, info);
			}

			connection.close();
			
		}catch (Exception e) {
				System.out.println(e.getMessage());
		}
		
		return infoStatus;
	}
}
	
