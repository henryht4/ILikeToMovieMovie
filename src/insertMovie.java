

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
		
		Connection con=DBConnection.getConnection();
		Constants cons= new Constants();
		
		CallableStatement csmt;
		try {
			csmt = con.prepareCall("{call add_movie(?,?,?,?,?)}");
			csmt.setString("titles", name);
			csmt.setInt("years", Integer.parseInt(year));
			csmt.setString("directors",director);
			csmt.setString("starName",star);
			csmt.setString("genreName",genre);
			csmt.registerOutParameter(11, 12);
			csmt.execute();
			String out = "";
			out = csmt.getString(11);
			response.sendRedirect("search.jsp?found="+out);
		} catch (SQLException e) {
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
