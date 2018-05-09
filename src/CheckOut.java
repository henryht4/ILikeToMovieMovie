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

//import helper classes
import helper.CheckOutInfo;
import helper.SaleActions;
import helper.ItemsInCart;


@WebServlet("/CheckOut")

public class CheckOut extends HttpServlet{


		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession(true);
			
			Boolean saleStatus = false; //initialize sale status 
			
			CheckOutInfo info = new CheckOutInfo(
					request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("customerID"),
					request.getParameter("creditCard"),
					request.getParameter("expDate"));
			
			
			SaleActions saleAction = new SaleActions(); //instance of saleAction class
			ItemsInCart cart = new ItemsInCart(); //use this to get the cart Arraylist
			
			try {
				saleStatus = saleAction.checkCustomerInfo(cart, info);

				if(saleStatus == true && cart.getItemsList() != null) {
					session.setAttribute("purchaseSuccess", "Your order was successfully placed!");
					cart.emptyCart();//empty cart after purchase
					RequestDispatcher rd = request.getRequestDispatcher("/confirmation.jsp");
					rd.forward(request, response);
					
				}
				//if there's nothing in the arraylist, checkout does not go through
				else if(cart.getItemsList() == null) {
					session.setAttribute("emptyCart", "There's nothing in your cart to check out!");
				}
				//if customer's information does not match, then 
				else {
					session.setAttribute("purchaseFail", "The information you entered was incorrect! Please try again");
					System.out.println("---Wrong User Info---");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
			request.getRequestDispatcher("/checkout.jsp").forward(request, response);

		}
}

