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
    User(String uid, String passwd) {
        this.uid= uid;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String uid) {
        this.uid = uid;
    }

    public void setPassword(String passwd) {
        this.passwd = passwd;
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
        return Objects.equals(this.id, user.id) && Objects.equals(this.uid, user.uid) && Objects.equals(this.passwd, user.passwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.uid, this.passwd);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", uid='" + this.uid+ '\'' + ", passwd='" + this.passwd + '\'' + '}';
    }
}