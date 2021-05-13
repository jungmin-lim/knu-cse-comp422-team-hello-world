package edu.knu.se.movierecommendation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;



public class AccessingDataApplication {
	public static void main(String[] args) {
		List<Movie> ret = new ArrayList<Movie>();
        BufferedReader br = null;
        
        
		try { 
			 br = Files.newBufferedReader(Paths.get("movie.csv"));
			String line = ""; 
			line = br.readLine();
			while((line = br.readLine()) != null){
                
                String array[] = line.split(",");
                
                Movie newMovie = new Movie(array[0],array[1],array[2]);
                ret.add(newMovie);
            }
			br.close(); 
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} 
		catch (IOException e) {
			e.printStackTrace(); 
		}
		
		
		try { 
			 br = Files.newBufferedReader(Paths.get("rating.csv"));
			String line = ""; 
			Movie ratedMovie;
			User ratedUser;
			long rate;
			MovieRating rating;
			
			line = br.readLine();
			while((line = br.readLine()) != null){
               
               String array[] = line.split(",");
               
               ratedMovie = findByMovieId(array[1]);
               ratedUser = findByUid(array[0]);
               rate=Long.parseLong(array[2]);
               rating=new MovieRating(ratedMovie,ratedUser,rate);
           }
			br.close(); 
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} 
		catch (IOException e) {
			e.printStackTrace(); 
		}
		
		
	}
}