

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import helper.DBConnection;

/**
 * Servlet implementation class insertStar
 */
@WebServlet("/insertStar")
public class insertStar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertStar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String name= (String)request.getParameter("name");
		String year=(String)request.getParameter("year");
		System.out.println(name);
			
		
		//Connection con=DBConnection.getConnection();
		System.out.println(name);
		
		
		PreparedStatement statement = null;
		
		try {
			//P5 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			Context initCtx = new InitialContext();

            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            
            // Look up our data source
            DataSource ds = (DataSource) envCtx.lookup("jdbc/MasterDB");
			
            Connection con=ds.getConnection();
			
            //P5~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
			
			String query="";
			ResultSet result = null;
			
			query="Select id from stars";
			statement = con.prepareStatement(query);
			String lastId="";
			
			result = statement.executeQuery();
			
			while(result.next())
			{
				
				lastId= result.getString(1);
			}
			System.out.println(lastId);
			
			
			int last= Integer.parseInt(lastId.substring(2));
			String starId=lastId.substring(0,2)+String.valueOf(last+1);
			
			if(year.trim().length()==0)
			{
				query = "INSERT INTO stars"
						+ "(id, name) VALUES"
						+ "(?,?)";
				
				statement= con.prepareStatement(query);
				statement.setString(1, starId);
				statement.setString(2, name);
			}
			else
			{
				System.out.println("ok");
				
				query = "INSERT INTO stars"
						+ "(id, name, birthyear) VALUES"
						+ "(?,?,?)";
				
				statement= con.prepareStatement(query);
				statement.setString(3, year);
				statement.setString(1, starId);
				statement.setString(2, name);
			}
			statement .executeUpdate();
			
			
			System.out.println("done");
			
			response.sendRedirect("dashboard.jsp?found=Star Inserted");
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
