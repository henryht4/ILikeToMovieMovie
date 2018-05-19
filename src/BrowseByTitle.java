

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.DBConnection;
import helper.MovieListing;

/**
 * Servlet implementation class BrowseByTitle
 */
@WebServlet("/BrowseByTitle")
public class BrowseByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseByTitle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String first= (String)request.getParameter("letter");
		
		Connection con=DBConnection.getConnection();
		
		
		PreparedStatement statement = null;
		try {
			
			String query="";
			ResultSet result = null;
		
			query="Select * from movies where left(title,1)=?";
			statement = con.prepareStatement(query);
			statement.setString(1, first);
			result = statement.executeQuery();
			
			ArrayList<MovieListing> movies= new ArrayList<MovieListing>();
			MovieListing movie= new MovieListing();
			ArrayList<String> movieId= new ArrayList<String>();
			ArrayList<MovieListing> list=null;
			
				while(result.next()){
					MovieListing movie2=new MovieListing();
					
					movie2.setTitle(result.getString(2));
					movie2.setYear(result.getInt(3));
					movie2.setDirector(result.getString(4));
					movie2.setId(result.getString(1));
					movies.add(movie2);
				}
				
				
			for(MovieListing item:movies)
			{
				query="Select rating from ratings where movieId=?";
				statement = con.prepareStatement(query);
				statement.setString(1, item.getId());
				result = statement.executeQuery();
				while(result.next()){
					item.setRating(result.getFloat(1));
				}
				
				query="Select starId from stars_in_movies where movieId=?";
				statement = con.prepareStatement(query);
				statement.setString(1, item.getId());
				result = statement.executeQuery();
				ArrayList<String> sid= new ArrayList<String>();
				while(result.next()){
					sid.add(result.getString(1));
				}
				item.setStarID(sid);
				
				ArrayList<String> sname= new ArrayList<String>();
				for(String id: sid)
				{
					query="Select name from stars where id=?";
					statement = con.prepareStatement(query);
					statement.setString(1, id);
					result = statement.executeQuery();
					
					while(result.next()){
						sname.add(result.getString(1));
					}
				}
				item.setStarName(sname);
				
				query="Select genreId from genres_in_movies where movieId=?";
				statement = con.prepareStatement(query);
				statement.setString(1, item.getId());
				result = statement.executeQuery();
				ArrayList<String> gid= new ArrayList<String>();
				while(result.next()){
					gid.add(result.getString(1));
				}
				item.setGenreID(gid);
				
				ArrayList<String> gname= new ArrayList<String>();
				for(String id: gid)
				{
					query="Select name from genres where id=?";
					statement = con.prepareStatement(query);
					statement.setString(1, id);
					result = statement.executeQuery();
					
					while(result.next()){
						gname.add(result.getString(1));
					}
				}
				item.setGenres(gname);
				
			}
				
	
			
			
			request.getSession().setAttribute("Movies", movies);
			response.sendRedirect("browseResults.jsp");
			
		}catch (SQLException e) {
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
