package edu.knu.se.movierecommendation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendationController {

    @GetMapping("/")
    public String indexPage() {
        return "Hello, world!";
    }

}
