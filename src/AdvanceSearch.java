

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import helper.SearchManager;
import helper.MovieListing;



/**
 * Servlet implementation class AdvanceSearch
 */
@WebServlet("/AdvanceSearch")
public class AdvanceSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	   
   

     
     
    public AdvanceSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        Gson gson = new Gson();
        JsonArray arrayObj=new JsonArray();
       
        String query = request.getParameter("term");
        System.out.println(query);
        query = query.toLowerCase();
        ArrayList<MovieListing> list=SearchManager.getAdvanceSearchResults(query);
        String json = new Gson().toJson(list);
        
        out.println(json);
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        Gson gson = new Gson();
        JsonArray arrayObj=new JsonArray();
       
        String query = request.getParameter("term");
        System.out.println(query);
        query = query.toLowerCase();
        /*for(int i=0; i<COUNTRIES.length; i++) {
            String country = COUNTRIES[i].toLowerCase();
            if(country.startsWith(query)) {
            	
                arrayObj.add(gson.fromJson(COUNTRIES[i], JsonElement.class));
            }
        }*/
       
        out.println(arrayObj.toString());
        out.close();
	}

}
