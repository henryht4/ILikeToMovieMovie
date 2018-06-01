

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.SearchManager;
import helper.MovieListing;

/**
 * Servlet implementation class NormalSearch
 */
@WebServlet("/NormalSearch")
public class NormalSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NormalSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("search")!=null){
			if(request.getParameter("title")=="" )
			{
				response.sendRedirect("search.jsp?found=error");
			}
			
			String title=request.getParameter("title");
			
			Integer year=0;
			if(!(request.getParameter("year")==null)){
				year=Integer.parseInt(request.getParameter("year"));
			}
			String director=request.getParameter("");
			String starName="";
			MovieListing movie=new MovieListing();
			movie.setTitle(title);
			movie.setYear(year);
			movie.setDirector("");
//			movie.setStarName(starName);
			ArrayList<MovieListing> list=SearchManager.getNormalSearchResults(movie,starName);
			if(!list.isEmpty()){
				request.getSession().setAttribute("Movies", list);
				response.sendRedirect("searchResult.jsp");
			}else{

				
				response.sendRedirect("search.jsp?found=error");
			}
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
