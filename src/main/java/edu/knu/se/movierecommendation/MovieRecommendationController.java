package edu.knu.se.movierecommendation;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String greeting() {
        return "Hello, world!";
    }

    @PutMapping("/users")
    public String addUser(User user) {
        boolean result = (userRepository.save(user) == null);
        return "{ \"result\": " + (result ? "SUCCESS" : "FAILED") + " }";
    }

    @DeleteMapping("/users")
    public String removeUser(String uid) {
        boolean result = (userRepository.removeByUid(uid) == null);
        return "{ \"result\": " + (result ? "SUCCESS" : "FAILED") + " }";
    }

    @GetMapping("/users")
    public String listUsers() {
        return userRepository.findAll().toString();
    }

    @GetMapping("/users/_count_")
    public String countUsers() {
        return Long.toString(userRepository.count());
    }

}
