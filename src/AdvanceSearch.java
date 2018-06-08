
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
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
import helper.MovieResult;



/**
 * Servlet implementation class AdvanceSearch
 */
@WebServlet("/AdvanceSearch")
public class AdvanceSearch extends HttpServlet {
	
	long startTimeTS = System.nanoTime();
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
        
        query = query.toLowerCase();
        long startTimeTJ = System.nanoTime();
        ArrayList<MovieResult> list=SearchManager.getAdvanceSearchResults(query);
        long endTimeTJ = System.nanoTime();
        long TJ = endTimeTJ - startTimeTJ;
        String json = new Gson().toJson(list);
        
        out.println(json);
        out.close();
        long endTimeTS = System.nanoTime();
        long TS = endTimeTS - startTimeTS;
        
        String contextPath = getServletContext().getRealPath("/");
        String xmlFilePath = contextPath + "\\log.txt";
        System.out.println(xmlFilePath);
        File logFile = new File(xmlFilePath);
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true), "UTF-8"));
        if(logFile.exists()) {
        	writer.append(TS+","+TJ+"\n");
        	writer.close();
        }else {
        	logFile.createNewFile();
        	writer.write(TS+","+TJ+"\n");
            writer.close();
        }
        
    	
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
        
       
        out.println(arrayObj.toString());
        out.close();
	}


}
