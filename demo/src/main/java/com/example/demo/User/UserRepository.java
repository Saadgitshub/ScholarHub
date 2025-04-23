package com.example.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByEmail(String email);
    
    Optional<User> findByEmail(String email);

}
