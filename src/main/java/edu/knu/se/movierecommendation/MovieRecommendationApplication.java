package edu.knu.se.movierecommendation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieRecommendationApplication {

	private static final Logger log = LoggerFactory.getLogger(MovieRecommendationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieRecommendationApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MovieRepository movieRepository, UserRepository userRepository, MovieRatingRepository movieRatingRepository) {
		return(args) -> {
			BufferedReader bufferedReader;

			log.info("Reading info from csv files");
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/movies.csv")));
				String line = bufferedReader.readLine();
				while((line = bufferedReader.readLine()) != null) {
					String movieInfo[] = line.split(",");
					Movie newMovie = new Movie(movieInfo[0], movieInfo[1], movieInfo[2]);

					movieRepository.save(newMovie);
				}

				bufferedReader.close();

			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}

			try {
				bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/ratings.csv")));
				String line = bufferedReader.readLine();

				while((line = bufferedReader.readLine()) != null) {
					String ratingInfo[] = line.split(",");

					if(!userRepository.existsByUid(ratingInfo[0])) {
						userRepository.save(new User(ratingInfo[0], "passwd"));
					}

					User user = userRepository.findByUid(ratingInfo[0]);

					if(!movieRepository.existsByMovieId(ratingInfo[1])) {
						System.out.println("MovieId " + ratingInfo[1] + " does not exists!");
						continue;
					}
					
					Movie movie = movieRepository.findByMovieId(ratingInfo[1]);
					MovieRating rating = new MovieRating(user, movie, Double.parseDouble(ratingInfo[2]));
					
					movieRatingRepository.save(rating);
				}
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		};
	}
}