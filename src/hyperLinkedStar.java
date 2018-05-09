

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.MovieListing;
import helper.Query;
import helper.StarListing;

/**
 * Servlet implementation class hyperLinkedStar
 */
@WebServlet("/hyperLinkedStar")
public class hyperLinkedStar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hyperLinkedStar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Query query = new Query();		
		
		ArrayList<String> genres = new ArrayList<String>();
		String id = request.getParameter("id");
		try {
			ArrayList<MovieListing> star = query.getStarsMovies(id);
			session.setAttribute("star",star);
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		session.setAttribute("genres", genres);
		request.getRequestDispatcher("hyperLinkedStar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}