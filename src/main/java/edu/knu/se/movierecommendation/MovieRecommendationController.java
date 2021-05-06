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
    public String greeting() {
        return "Hello, world!";
    }

    @Transactional
    @PutMapping(value = "/users", produces = "aplication/json; charset=UTF-8")
    public String addUser(String uid, String passwd) {
        if (userRepository.findByUid(uid) != null) {
            // An user with this uid already exists.
            return "{ \"result\": \"FAILED\" }";
        }
        boolean saveResult = (userRepository.save(new User(uid, passwd)) != null);
        return "{ \"result\": " + (saveResult ? "SUCCESS" : "FAILED") + " }";
    }

    @Transactional
    @DeleteMapping(value = "/users", produces = "aplication/json; charset=UTF-8")
    public String removeUser(String uid) {
        boolean result = !userRepository.removeByUid(uid).isEmpty();
        return "{ \"result\": " + (result ? "SUCCESS" : "FAILED") + " }";
    }

    @GetMapping(value = "/users", produces = "application/json; charset=UTF-8")
    public String listUsers() {
        return userRepository.findAll().toString();
    }

    @GetMapping(value = "/users/_count_", produces = "application/json; charset=UTF-8")
    public String countUsers() {
        return Long.toString(userRepository.count());
    }

}
