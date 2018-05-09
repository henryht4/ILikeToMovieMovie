

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.Movie;
import helper.Query;

/**
 * Servlet implementation class hyperLinkedMovie
 */
@WebServlet("/hyperLinkedMovie")
public class hyperLinkedMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hyperLinkedMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Query query = new Query();		
		//ArrayList<String> genres = new ArrayList<String>();
		String id = request.getParameter("id");
		try {
			Movie movie = query.getMovie(id);
			session.setAttribute("movie", movie);	
			
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//session.setAttribute("genres", genres);
		request.getRequestDispatcher("hyperlinkedMovie.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
