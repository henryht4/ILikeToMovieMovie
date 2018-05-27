package helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection con=null;
	public static Connection getConnection(){
		try{
			if(con==null){
				Class.forName("com.mysql.jdbc.Driver");
				//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb","root","FromJae1994");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb","mytestuser","mypassword");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return con;
	}
}
