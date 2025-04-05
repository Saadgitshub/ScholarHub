package com.example.demo.User;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.subclasses.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Find user by ID
    @GetMapping("/{id}")
    User findById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        return user.get();
    }

    // ✅ Find user by username
    @GetMapping("/username/{username}")
    User findByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + username + " not found.");
        }
        return user.get();
    }

    // ✅ Find user by email
    @GetMapping("/email/{email}")
    User findByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email " + email + " not found.");
        }
        return user.get();
    }

    // ✅ Create user
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody User user) {
        userRepository.create(user);
    }

    // ✅ Update user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody User user, @PathVariable Integer id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found.");
        }
        userRepository.update(user, id);
    }

    // ✅ Delete user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found.");
        }
        userRepository.delete(id);
    }

    // ✅ Fetch all students
    @GetMapping("/students")
    List<Student> findStudents() {
        return userRepository.findStudents();
    }

    // ✅ Fetch all teachers
    @GetMapping("/teachers")
    List<Teacher> findTeachers() {
        return userRepository.findTeachers();
    }

    // ✅ Fetch all admins
    @GetMapping("/admins")
    List<Admin> findAdmins() {
        return userRepository.findAdmins();
    }
}
