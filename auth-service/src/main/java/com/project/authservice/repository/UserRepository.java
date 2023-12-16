package com.project.authservice.repository;

import com.project.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("select u from User u left join fetch u.roles where u.username = ?1")
    Optional<User> findByUsernameEagerly(String username);
}
