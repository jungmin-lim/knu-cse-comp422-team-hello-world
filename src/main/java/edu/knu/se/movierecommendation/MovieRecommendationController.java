package edu.knu.se.movierecommendation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendationController {

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

}
