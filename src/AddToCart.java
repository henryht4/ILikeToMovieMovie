

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.CartItem;
import helper.Customer;
import helper.ItemsInCart;
import helper.MovieListing;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//keeps everything in 1 session
		HttpSession session = request.getSession(true);
		
		//arraylist of movies from CartItem
		ArrayList<CartItem> items = (ArrayList<CartItem>) session.getAttribute("items");
		Customer cust= (Customer)request.getSession().getAttribute("cust");
		String movieId= request.getParameter("movieId");
		ArrayList<MovieListing> movies = (ArrayList<MovieListing>) session.getAttribute("movies");
		MovieListing movie = null;
		
		
		boolean found=false;
		for(CartItem i:items)
		{
			if(movieId.equals(i.getMovie().getId()))
			{
				movie=i.getMovie();
				i.addQuantity();
				found=true;
			}
			
		}
		
		if(!found)
		{
			for(MovieListing m:movies)
			{
				if(m.getId().equals(movieId))
				{
					movie=m;
					CartItem item= new CartItem(m,1);
					items.add(item);
				}
			}
		}
		
		int total = 0;
		for(CartItem i : items){
			total += i.getQuantity()*10;
		}
		
		
		session.setAttribute("total", total);
		RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
