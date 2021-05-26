package edu.knu.se.movierecommendation;

import org.springframework.data.jpa.repository.JpaRepository;

interface MovieRatingRepository extends JpaRepository<MovieRating, MovieRatingKey> {
    
}