package edu.knu.se.movierecommendation;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Movie {
    private @Id @GeneratedValue Long id;
    private String movieId;
    private String title;
    private String[] genres;

    Movie() {}

    Movie(String movieId, String title, String genreList) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genreList.split("\\|");
    }

    public Long getId() {
        return this.id;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public String getTitle() {
        return this.title;
    }

    public String[] getGenres() {
        return this.genres;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(String genreList) {
        this.genres = genreList.split("\\|");
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(!o.getClass().equals(Movie.class)) {
            return false;
        }

        Movie movie = (Movie) o;
        return Objects.equals(this.id, movie.id) && Objects.equals(this.movieId, movie.movieId) && Objects.equals(this.title, movie.title) && Objects.equals(this.genres, movie.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id , this.movieId, this.title, this.genres);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", movieId='" + this.movieId + '\'' + ", title='" + this.title + '\'' + ", genres='" + this.genres + '\'' + '}';
    }
}