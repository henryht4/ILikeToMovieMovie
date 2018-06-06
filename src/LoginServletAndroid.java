
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

import com.google.gson.Gson;

import helper.CartItem;
import helper.Customer;
import helper.LoginResponse;

/**
 * Servlet implementation class LoginServletAndroid
 */
@WebServlet(name = "/LoginServletAndroid", urlPatterns = "/api/android-login")
public class LoginServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletAndroid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
        LoginResponse auth = new  LoginResponse();
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
		
        	
        	//Statement statement = con.createStatement();
			
			String email = request.getParameter("email");
			String password = request.getParameter("pass");
			System.out.println(email);
			System.out.println(password);
			String query ="SELECT * from customers where email=?";

			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet result = pst.executeQuery();
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
			
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
				System.out.println(encryptedPassword);
				// use the same encryptor to compare the user input password with encrypted password stored in DB
				success = new StrongPasswordEncryptor().checkPassword(password, encryptedPassword);
				System.out.println(success);
				
				if(success) {
					auth.setAuth(true);
				String json = new Gson().toJson(auth);
			    
			    response.getWriter().write(json);
			    }
				else
				{
					auth.setAuth(false);
					String json = new Gson().toJson(auth);
				   
				    response.getWriter().write(json);
				}
			}
        }
         catch (Exception e) {
        	String json = new Gson().toJson(e.getMessage());
		    
		    response.getWriter().write(json);
    }
    
   
    
	        
	        
	    

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
