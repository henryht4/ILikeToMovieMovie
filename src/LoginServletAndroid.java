
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.google.gson.Gson;

import helper.CartItem;
import helper.Customer;
import helper.LoginResponse;

/**
 * Servlet implementation class LoginServletAndroid
 */
@WebServlet("/LoginServletAndroid")
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
		
		
		String email= request.getParameter("email");
		String pass=request.getParameter("pass");
		
		System.out.println(email);
		System.out.println(pass);
			
		
		//sql login
		String loginUser = "root";
        String loginPasswd = "root";
        String loginUrl = "jdbc:mysql://localhost/moviedb";
        
       
        
        
        //get writer
       
        
        
        LoginResponse auth = new  LoginResponse();
       
        Connection conn=null;
		 PreparedStatement stmt = null;
		 int rows=0;
		 boolean flag=true;
		 response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
        
        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	
        	//create database connection
        	conn = DriverManager.getConnection(loginUrl,loginUser,loginPasswd);
        	
        	String query="Select *from customers";
			stmt = conn.prepareStatement(query);
			ResultSet rs1  = stmt.executeQuery();
			
			
			while(rs1.next() && flag){
				if(rs1.getString(6).equalsIgnoreCase(email) ){
					Customer cust= new Customer();
					cust.setId(rs1.getInt(1));
			        cust.setFname(rs1.getString(2));
			        cust.setLname(rs1.getString(3));
			        cust.setCcid(rs1.getString(4));
			        cust.setAddress(rs1.getString(5));
			        cust.setEmail(rs1.getString(6));
			        cust.setPassword(rs1.getString(7));
			        // get the encrypted password from the database
					String encryptedPassword = rs1.getString("password");
					
					// use the same encryptor to compare the user input password with encrypted password stored in DB
					boolean success = new StrongPasswordEncryptor().checkPassword(pass, encryptedPassword);
					
					
					if(success)
						flag=false;
					
					
				}
			}
			
			
			
			stmt.close();
			conn.close();
    		
    		
			if(flag)
			{

				auth.setAuth(false);
				String json = new Gson().toJson(auth);
			    
			    response.getWriter().write(json);
			}
			else
			{
				 
				auth.setAuth(true);
				String json = new Gson().toJson(auth);
			   
			    response.getWriter().write(json);
			}
			
        } catch (Exception e) {
        	String json = new Gson().toJson(auth);
		    
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
