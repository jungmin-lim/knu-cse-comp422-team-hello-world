package edu.knu.se.movierecommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendationController {
    private UserRepository userRepository;

    @Autowired
    public MovieRecommendationController(UserRepository userRepository) {
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
    public Result listUsers() {
        Result result = new Result();
        result.setResult(userRepository.findAll().toString());
        return result;
    }

    @GetMapping(value = "/users/_count_")
    public Result countUsers() {
        Result result = new Result();
        result.setResult(Long.toString(userRepository.count()));
        return result;
    }
}
