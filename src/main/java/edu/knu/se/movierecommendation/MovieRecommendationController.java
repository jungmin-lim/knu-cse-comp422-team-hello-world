package edu.knu.se.movierecommendation;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendationController {
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @Autowired
    public MovieRecommendationController(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/")
    public Result greeting() {
        Result result = new Result();
        result.setResult("Hello, world!");
        return result;
    }

    @Transactional
    @PutMapping(value = "/users")
    public Result addUser(String uid, String passwd) {
        Result result = new Result();
        if (userRepository.findByUid(uid) != null) {
            // An user with this uid already exists.
            result.setResult("FAILED");
            return result;
        }
        boolean saveResult = (userRepository.save(new User(uid, passwd)) != null);
        result.setResult(saveResult ? "SUCCESS" : "FAILED");
        return result;
    }

    @Transactional
    @DeleteMapping(value = "/users")
    public Result removeUser(String uid) {
        Result result = new Result();
        boolean removeResult = !userRepository.removeByUid(uid).isEmpty();
        result.setResult(removeResult ? "SUCCESS" : "FAILED");
        return result;
    }

    @GetMapping(value = "/users")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/users/_count_")
    public Long countUsers() {
        return userRepository.count();
    }

    @GetMapping(value = "/users/{id}")
    public List<String> getRatedMovies(String uid) {
        Set<MovieRating> ratings = userRepository.findByUid(uid).getRatings();
        List<String> ratedMovies = new ArrayList<Long>(ratings.size());
        for(MovieRating x : ratings) {
            ratedMovies.add(x.getMovie().getMovieId());
        }

        return ratedMovies;
    }
}
