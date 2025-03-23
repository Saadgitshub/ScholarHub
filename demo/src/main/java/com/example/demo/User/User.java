package com.example.demo.User;
public record User(
        Integer id,
        String name,
        String username,
        String email,
        String password,
        Role role // ADMIN, TEACHER, or STUDENT
) {
}