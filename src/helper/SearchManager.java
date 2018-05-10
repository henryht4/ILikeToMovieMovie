package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import helper.DBConnection;
import helper.MovieListing;

public class SearchManager {
	public static ArrayList<MovieListing> getSearchResults(MovieListing movie, String sn){
		ArrayList<MovieListing> list=new ArrayList<>();
		try{
			Connection con=DBConnection.getConnection();
			String query="SELECT  m.title,m.`year`,m.director,s.name,r.rating ,sim.`movieID`, s.`id` FROM movies m JOIN stars_in_movies sim "  
						+"ON m.`id`=sim.`movieID` JOIN stars s ON s.`id`=sim.`starID` JOIN ratings r ON r.`movieID`=m.`id` " 
						+"WHERE m.title LIKE ? AND m.`year` LIKE ?  AND m.director LIKE ? AND s.name LIKE ?";
			
			
			
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, "%"+movie.getTitle()+"%");
			if(movie.getYear()!=null){
				pst.setString(2, "%"+movie.getYear().toString()+"%");
			}else{
				pst.setString(2, "%%");
			}
			pst.setString(3, "%"+movie.getDirector()+"%");
			pst.setString(4, "%"+sn+"%");
			
			ResultSet rst=pst.executeQuery();
			while(rst.next()){
				MovieListing movie2=new MovieListing();
				movie2=new MovieListing();
				
				movie2.setTitle(rst.getString(1));
				movie2.setYear(rst.getInt(2));
				movie2.setDirector(rst.getString(3));
				movie2.setName(rst.getString(4));
				movie2.setRating(rst.getFloat(5));
				movie2.setId((rst.getString(6)));
				movie2.setStarid(rst.getString(7));
				list.add(movie2);
			}
		
			if(list.size()!=0)
			{
				ArrayList<MovieListing> result = new ArrayList<MovieListing>();
				Set<String> titles = new HashSet<String>();

				for( MovieListing item : list ) {
				    if( titles.add( item.getTitle() )) {
				        result.add( item );
				    }
				}
				
				
				for(int i=0;i<result.size();i++)
				{
					ArrayList<String> name= new ArrayList<String>();
					ArrayList <String> id= new ArrayList<String>();
					for(int j=0;j<list.size();j++)
					{
						
						if(result.get(i).getId().equals(list.get(j).getId()))
						{
							name.add(list.get(j).getName());
							id.add(list.get(j).getStarid());
						}
						
					}
					
					
					result.get(i).setStarID(id);
					result.get(i).setStarName(name);
				}
			
				
				for( MovieListing item : result ) {
					
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
				return result;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
}
