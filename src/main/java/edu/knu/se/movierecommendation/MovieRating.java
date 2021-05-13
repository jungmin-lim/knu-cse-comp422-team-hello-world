package edu.knu.se.movierecommendation;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "movie_rating")
public class MovieRating {
    @EmbeddedId
    MovieRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name="movie_id")
    private Movie movie;

    @Column(name = "rating")
    private double rating;

    public MovieRating() {}

    public MovieRating(User user, Movie movie, double rating) {
        this.id = new MovieRatingKey(user.getId(), movie.getId());
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }

    public MovieRatingKey getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getRating() {
        return rating;
    }

    public void setId(MovieRatingKey id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.user, this.movie, this.rating);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        
        if(!this.getClass().equals(o.getClass())) {
            return false;
        }
        
        MovieRating rating = (MovieRating) o;
        return Objects.equals(this.id, rating.id) && Objects.equals(this.user, rating.user) && Objects.equals(this.movie, rating.movie) && Objects.equals(this.rating, rating.rating);
    }
}