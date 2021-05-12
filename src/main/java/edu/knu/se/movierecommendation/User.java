package edu.knu.se.movierecommendation;

import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id 
    @GeneratedValue 
    @Column(name="id")
    private Long id;

    private String uid;
    private String passwd;

    @OneToMany 
    @JoinTable(mappedBy="user")
    private Set<MovieRating> ratings = new HashSet<> ();

    User() {}

    User(String uid, String passwd) {
        this.uid = uid;
        this.passwd = passwd;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.uid;
    }

    public String getPassword() {
        return this.passwd;
    }

    public Set<MovieRating> getRatings() {
        return this.ratings;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String uid) {
        this.uid = uid;
    }

    public void setPassword(String passwd) {
        this.passwd = passwd;
    }

    public void setRatings(Set<MovieRating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o.getClass().equals(User.class))) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.uid, user.uid) 
            && Objects.equals(this.passwd, user.passwd) && Objects.equals(this.ratings, user.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.uid, this.passwd, this.ratings);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", uid='" + this.uid+ '\'' 
            + ", passwd='" + this.passwd + '\'' + ", ratings='" + this.ratings + '\'' + '}';
    }
}