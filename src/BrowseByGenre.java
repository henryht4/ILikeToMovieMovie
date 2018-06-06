

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import helper.MovieListing;

/**
 * Servlet implementation class BrowseByGenre
 */
@WebServlet("/BrowseByGenre")
public class BrowseByGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseByGenre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String genre= (String)request.getParameter("genre");
		String genreId="";
		
		//Connection con=DBConnection.getConnection();
		
		PreparedStatement statement = null;
		try {
			//P5 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			Context initCtx = new InitialContext();

            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            
            // Look up our data source
            DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			
            Connection con=ds.getConnection();
			
            //P5~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
            String query="";
			ResultSet result = null;
		
			query="Select id from genres where name=?";
			statement = con.prepareStatement(query);
			statement.setString(1, genre);
			result = statement.executeQuery();
			if(result.next())
			{
				genreId= result.getString(1);
			}
			ArrayList<MovieListing> movies= new ArrayList<MovieListing>();
			MovieListing movie= new MovieListing();
			ArrayList<String> movieId= new ArrayList<String>();
			ArrayList<MovieListing> list=null;
			query="Select movieId from genres_in_movies where genreId=?";
			statement=con.prepareStatement(query);
			statement.setString(1, genreId);
			result = statement.executeQuery();
			while(result.next()) {
				movieId.add(result.getString(1));
			
			}
			
			for(String id:movieId)
			{
				
				query="SELECT  * from movies where id=?";
				
				statement=con.prepareStatement(query);
				statement.setString(1, id);
				result = statement.executeQuery();
				while(result.next()){
					MovieListing movie2=new MovieListing();
					
					movie2.setTitle(result.getString(2));
					movie2.setYear(result.getInt(3));
					movie2.setDirector(result.getString(4));
					movie2.setId(id);
					movies.add(movie2);
				}
				
				
				
				}
			
			for( MovieListing item : movies ) {
				query="SELECT  * from stars_in_movies where movieId=?";
				statement=con.prepareStatement(query);
				statement.setString(1, item.getId());
				result = statement.executeQuery();
				ArrayList<String> stars= new ArrayList<String>();
				ArrayList<String> starId= new ArrayList<String>();
				while(result.next()){
					starId.add(result.getString(1));
				}
				
				item.setStarID(starId);
				for(String ids: starId)
				{
					query="SELECT  * from stars where id=?";
					statement=con.prepareStatement(query);
					statement.setString(1, ids);
					result = statement.executeQuery();
					
					while(result.next()){
						stars.add(result.getString(2));
					}
				}
				
				item.setStarName(stars);
				
				query="SELECT  * from ratings where movieId=?";
				statement=con.prepareStatement(query);
				statement.setString(1, item.getId());
				result = statement.executeQuery();
				while(result.next()){
					item.setRating(result.getFloat(2));
				}
				
			}
			
			for( MovieListing item : movies ) {
				
				ArrayList <String> genres= new ArrayList<String>();
				ArrayList <String> genresId= new ArrayList<String>();
				String query1="Select genreId from genres_in_movies where movieId = ?";
				PreparedStatement pmst=con.prepareStatement(query1);
				pmst.setString(1, item.getId());
				ResultSet rs=pmst.executeQuery();
				
				while(rs.next()){
					String query2="Select name from genres where id=?";
					PreparedStatement pmst1=con.prepareStatement(query2);
					pmst1.setString(1, rs.getString(1));
					ResultSet rs1=pmst1.executeQuery();
					genresId.add(rs.getString(1));
					
					while(rs1.next()){
						genres.add(rs1.getString(1));
					}
				}
				
				item.setGenreID(genresId);
				item.setGenres(genres);
			}
	
			
			
			request.getSession().setAttribute("Movies", movies);
			response.sendRedirect("browseResults.jsp");
			
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
