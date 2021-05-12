package edu.knu.se.movierecommendation;

import javax.persistence.*;

@Entity
@Table(name = "movie_rating")
public class MovieRating {
    @EmbeddedId
    MovieRatigKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name="movie_id")
    private Movie movie;

    @Column(name = "rating")
    private Long rating;

    public MovieRating() {}

    public MovieRatingKey getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public Long getRating() {
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

    public void setRating(Long rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Object.hash(this.id, this.user, this.movie, this.rating);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        
        if(!this.getClass().equals(o.getClass())) {
            return false;
        }

        return Object.equals(this.id, o.id) && Object.equals(this.user, o.user) && Object.equals(this.movie, o.movie) && Object.equals(this.rating, o.rating);
    }
}