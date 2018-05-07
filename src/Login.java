
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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


@WebServlet("/Login")

public class Login extends HttpServlet {

	public Login() {}; //public constructor
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
        
        try {
        	        	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection connection = DriverManager.getConnection(loginUrl,loginUser,loginPasswd);
			Statement statement = connection.createStatement();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			String query = "SELECT * FROM customers WHERE email = '" + email + "' "
					+ "AND password = '" + password + "'";
			System.out.println(query);
			ResultSet result = statement.executeQuery(query);

			if(result.next()) {
		        HttpSession session = request.getSession();//save user email in session scope
		        session.setAttribute("email", email);
				response.sendRedirect("index.jsp");
			}
			else {

				request.setAttribute("errorMessage", "Invalid username or password! Please try again");
				System.out.println("wrong pw");
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
