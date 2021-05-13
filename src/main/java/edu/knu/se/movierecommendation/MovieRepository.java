package edu.knu.se.movierecommendation;


import org.springframework.data.jpa.repository.JpaRepository;

interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByMovieId(String movieId);
    boolean existsByMovieId(String movieId);
}