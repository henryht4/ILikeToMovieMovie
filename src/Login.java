
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mysql.jdbc.ResultSetMetaData;

import helper.CartItem;
import helper.Customer;
import helper.ItemsInCart;

import java.util.ArrayList;



@WebServlet("/Login")

public class Login extends HttpServlet {

	public Login() {}; //public constructor
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

		String loginUser = "root";
        String loginPasswd = "FromJae1994";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
        
        try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection connection = DriverManager.getConnection(loginUrl,loginUser,loginPasswd);
			
			Statement statement = connection.createStatement();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			String query ="SELECT * from customers where email=?";
			
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, email);
			ResultSet result = pst.executeQuery();

			boolean success = false;
			
			if(result.next()) {
				Customer cust= new Customer();
		        HttpSession session = request.getSession();//save user email in session scope
		        ArrayList<CartItem> items = new ArrayList<CartItem>();
		        
		        cust.setId(result.getInt(1));
		        cust.setFname(result.getString(2));
		        cust.setLname(result.getString(3));
		        cust.setCcid(result.getString(4));
		        cust.setAddress(result.getString(5));
		        cust.setEmail(result.getString(6));
		        cust.setPassword(result.getString(7));
		        
		        session.setAttribute("items", items);
		        session.setAttribute("email", email);
		        session.setAttribute("cust", cust);
		        
		        // get the encrypted password from the database
				String encryptedPassword = result.getString("password");
				
				// use the same encryptor to compare the user input password with encrypted password stored in DB
				success = new StrongPasswordEncryptor().checkPassword(password, encryptedPassword);
				
				
				if(success)
					response.sendRedirect("index.jsp");
				else
				{
					request.setAttribute("errorMessage", "Invalid username or password! Please try again");
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				}
			}
			else {
				
				
				request.setAttribute("errorMessage", "Invalid username or password! Please try again");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}
			
			connection.close();
			statement.close();
			result.close();
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
