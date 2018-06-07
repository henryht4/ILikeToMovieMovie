

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
import helper.*;

/**
 * Servlet implementation class insertMovie
 */
@WebServlet("/insertMovie")
public class insertMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name= (String)request.getParameter("title");
		String year=(String)request.getParameter("year");
		String director= (String)request.getParameter("director");
		String star=(String)request.getParameter("star");
		String genre=(String)request.getParameter("genre");
			
		
		//Connection con=DBConnection.getConnection();
		Constants cons= new Constants();
		
		CallableStatement csmt;
		try {
			//P5 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			Context initCtx = new InitialContext();

            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            
            // Look up our data source
            DataSource ds = (DataSource) envCtx.lookup("jdbc/MasterDB");
			
            Connection con=ds.getConnection();
			
            //P5~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
			csmt = con.prepareCall("{call add_movie(?,?,?,?,?,?)}");
			csmt.setString("titles", name);
			csmt.setInt("years", Integer.parseInt(year));
			csmt.setString("directors",director);
			csmt.setString("starName",star);
			csmt.setString("genreName",genre);
			csmt.registerOutParameter(6, Types.VARCHAR);
			csmt.execute();
			String out = "";
			out = csmt.getString(6);
			response.sendRedirect("dashboard.jsp?found="+out);
		} catch (SQLException e) {
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
