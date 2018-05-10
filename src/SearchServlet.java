


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import helper.SearchManager;
import helper.MovieListing;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("search")!=null){
			
			if(request.getParameter("title")=="" && request.getParameter("year")=="" &&
					request.getParameter("director")=="" && request.getParameter("starName")=="")
			{
				request.setAttribute("errorMessage", "Please enter missing information");
				 request.getRequestDispatcher("/search.jsp").forward(request, response);
			}
			
			String title=request.getParameter("title");
			Integer year=0;
			if(!request.getParameter("year").equals("")){
				year=Integer.parseInt(request.getParameter("year"));
			}
			String director=request.getParameter("director");
			String starName=request.getParameter("starName");
			MovieListing movie=new MovieListing();
			movie.setTitle(title);
			movie.setYear(year);
			movie.setDirector(director);
//			movie.setStarName(starName);
			ArrayList<MovieListing> list=SearchManager.getSearchResults(movie,starName);
			if(!list.isEmpty()){
				request.getSession().setAttribute("Movies", list);
				response.sendRedirect("searchResult.jsp");
			}else{
				response.sendRedirect("search.jsp?found=error");
			}
		}
	}

}
