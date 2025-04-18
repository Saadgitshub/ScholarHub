package com.example.demo.subclasses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // No extra methods yet, but you can add when needed
}
