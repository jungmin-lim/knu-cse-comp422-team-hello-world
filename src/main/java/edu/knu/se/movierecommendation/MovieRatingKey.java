package edu.knu.se.movierecommendation;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MovieRatingKey implements Serializable { 

    @Column(name="user_id")
    Long userId;

    @Column(name="movie_id")
    Long movieId;

    public MovieRatingKey() {}

    public MovieRatingKey(Long userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public int hashCode() {
        return Object.hash(this.userId, this.movieId) ;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!this.getClass().equals(o.getClass)) {
            return false;
        }

        return Object.equals(this.userId, o.userId) && Object.equals(this.movieId, o.movieId);
    }
}
