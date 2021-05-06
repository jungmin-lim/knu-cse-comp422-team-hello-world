package edu.knu.se.movierecommendation;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class User {
    private @Id @GeneratedValue Long id;
    private String uid;
    private String passwd;

    User() {}
    User(String username, String password) {
        this.username = name;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof User)) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.username, user.username) && Objects.equals(this.password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.password);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", username='" + this.username + '\'' + ", password='" + this.password + '\'' + '}';
    }
}