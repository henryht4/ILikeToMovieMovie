
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mysql.jdbc.ResultSetMetaData;

import helper.CartItem;
import helper.Customer;
import helper.ItemsInCart;
import helper.RecaptchaVerification;


import java.util.ArrayList;




@WebServlet("/Login")

public class Login extends HttpServlet {

	public Login() {}; //public constructor
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        PrintWriter out = response.getWriter();

		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
	 // System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
		
		//VERIFIES Recaptcha
		try {
			RecaptchaVerification.verify(gRecaptchaResponse);
		}catch(Exception e) {
			out.println("<html>");
            out.println("<head><title>Recaptcha Error</title></head>");
            //out.println("<link rel=\"stylesheet\" href=\"style.css\">");
            out.println("<body>");
            out.println("<p>There was a Recaptcha verification error! Please go back and make sure you are not a computer!</p>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body>");
            out.println("</html>");
            
            out.close();
			return;
		}
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
        
        try {

			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//Connection connection = DriverManager.getConnection(loginUrl,loginUser,loginPasswd);
        	//P5 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			Context initCtx = new InitialContext();

            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            
            // Look up our data source
            DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			
            Connection con=ds.getConnection();
			
            //P5~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
        	
        	Statement statement = con.createStatement();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			String query ="SELECT * from customers where email=?";

			PreparedStatement pst=con.prepareStatement(query);
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
				
				query ="SELECT * from employees where email=?";

				 pst=con.prepareStatement(query);
				 pst.setString(1, email);
				 result = pst.executeQuery();

				 success = false;

				if(result.next()) {
					
					  // get the encrypted password from the database
					String encryptedPassword = result.getString("password");
					
					// use the same encryptor to compare the user input password with encrypted password stored in DB
					success = new StrongPasswordEncryptor().checkPassword(password, encryptedPassword);
					
					if(success)
					{
						RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
						rd.forward(request, response);
						
					}
					else
					{
						
						request.setAttribute("errorMessage", "Invalid username or password! Please try again");
						RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
						rd.forward(request, response);
					}
					
				}
				else
				{
					
				request.setAttribute("errorMessage", "Invalid username or password! Please try again");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
				}
			}
			
			con.close();
			statement.close();
			result.close();
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	
		
	}
}
