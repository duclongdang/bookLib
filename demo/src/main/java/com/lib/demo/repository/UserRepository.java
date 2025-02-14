package com.lib.demo.repository;

import com.lib.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
