package com.example.demo.User;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Primary
class InMemoryUserRepository implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(users.stream()
                .filter(user -> user.id() == id)
                .findFirst()
                .orElseThrow(UserNotFoundException::new));
    }

    public void create(User user) {
        User newUser = new User(user.id(), user.name(), user.username(), user.email(), user.password(), user.role());
        users.add(newUser);
    }

    public void update(User newUser, Integer id) {
        Optional<User> existingUser = findById(id);
        if (existingUser.isPresent()) {
            var user = existingUser.get();
            log.info("Updating Existing User: " + user);
            users.set(users.indexOf(user), newUser);
        }
    }

    public void delete(Integer id) {
        log.info("Deleting User: " + id);
        users.removeIf(user -> user.id().equals(id));
    }

    public int count() {
        return users.size();
    }

    public void saveAll(List<User> users) {
        users.forEach(this::create);
    }

    public List<User> findByRole(Role role) {
        return users.stream()
                .filter(user -> Objects.equals(user.role(), role))
                .toList();
    }

    @PostConstruct
    private void init() {
        // Example data for testing
        users.add(new User(1, "John Doe", "johndoe", "john@example.com", "password123", Role.STUDENT));
        users.add(new User(2, "Jane Smith", "janesmith", "jane@example.com", "password456", Role.TEACHER));
    }
}
