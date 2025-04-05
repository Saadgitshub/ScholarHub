package com.example.demo.User;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.example.demo.subclasses.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> findById(Integer id) {
        return users.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> Objects.equals(user.getUsername(), username))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public void update(User newUser, Integer id) {
        findById(id).ifPresent(existingUser -> {
            int index = users.indexOf(existingUser);
            users.set(index, newUser);
            log.info("Updated user with ID: " + id);
        });
    }

    @Override
    public void delete(Integer id) {
        users.removeIf(user -> Objects.equals(user.getId(), id));
        log.info("Deleted user with ID: " + id);
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public void saveAll(List<User> users) {
        this.users.addAll(users);
    }

    // ✅ Fetch students
    @Override
    public List<Student> findStudents() {
        return users.stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user) // Cast to Student
                .toList();
    }

    // ✅ Fetch teachers
    @Override
    public List<Teacher> findTeachers() {
        return users.stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user) // Cast to Teacher
                .toList();
    }

    // ✅ Fetch admins
    @Override
    public List<Admin> findAdmins() {
        return users.stream()
                .filter(user -> user instanceof Admin)
                .map(user -> (Admin) user) // Cast to Admin
                .toList();
    }

    @PostConstruct
    private void init() {
        log.info("InMemoryUserRepository initialized.");
    }
}
