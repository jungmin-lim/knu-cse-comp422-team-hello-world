package edu.knu.se.movierecommendation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);
    List<User> removeByUid(String uid);
}
