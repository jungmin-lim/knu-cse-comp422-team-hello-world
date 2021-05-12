package edu.knu.se.movierecommendation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByMovieId(String movieId);
}