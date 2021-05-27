package edu.knu.se.movierecommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendationController {
    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private MovieRatingRepository movieRatingRepository;
    @Autowired
    public MovieRecommendationController(MovieRepository movieRepository, UserRepository userRepository,MovieRatingRepository movieRatingRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.movieRatingRepository = movieRatingRepository;
    }

    @GetMapping(value = "/")
    public Result greeting() {
        Result result = new Result();
        result.setResult("Hello, world!");
        return result;
    }

    @PutMapping(value = "/users/")
    public Result addUser(@RequestParam(value = "uid", required = true) String uid, @RequestParam(value = "passwd", required = true) String passwd) {
        Result result = new Result();
        if (!userRepository.existsByUid(uid)) {
        	userRepository.save(new User(uid, passwd));
            result.setResult("SUCCESS");
            return result;
        }
        result.setResult("FAILED");
        
        return result;
    }

    @Transactional
    @PutMapping(value = "/users/{userid}/ratings")
    public Result addMovieRating(
            @PathVariable(name = "userid") String uid,
            @RequestParam(value = "movie", required = true) String movieId,
            @RequestParam(value = "rating", required = true) double rating) {
        var result = new Result();

        var user = userRepository.findByUid(uid);
        if (user == null) {
            result.setResult("FAILED");
            return result;
        }

        var movie = movieRepository.findByMovieId(movieId);
        if (movie == null) {
            result.setResult("FAILED");
            return result;
        }

        var ratings = user.getRatings();
        movieRatingRepository.save(new MovieRating(user, movie, rating));
        result.setResult("SUCCESS");

        return result;
    }

    @Transactional
    @DeleteMapping(value = "/users/")
    public Result removeUser(@RequestParam(value = "uid", required = true) String uid) {
        Result result = new Result();
        boolean removeResult = !userRepository.removeByUid(uid).isEmpty();
        result.setResult(removeResult ? "SUCCESS" : "FAILED");
        return result;
    }

    @GetMapping(value = "/users")
    public List<String> listUsers() {
        List<User> users=userRepository.findAll();
        List<String> uids= new ArrayList<String>(users.size());
        for(User x : users) {
            uids.add(x.getUsername());
        }

        return uids;
    }

    @GetMapping(value = "/users/_count_")
    public Long countUsers() {
        return userRepository.count();
    }

    @GetMapping(value = "/users/{uid}/ratings")
    public Map<String,Double> getRatedMovies(@PathVariable(name = "uid") String uid) {
        Set<MovieRating> ratings = userRepository.findByUid(uid).getRatings();
        Map<String,Double> ratedMovies = new HashMap<String, Double>();
        for(MovieRating x : ratings) {
            ratedMovies.put(x.getMovie().getMovieId(),x.getRating());
        }

        return ratedMovies;
    }
}
