package com.example.demo.User;
import com.example.demo.subclasses.*;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void create(User user);

    void update(User user, Integer id);

    void delete(Integer id);

    int count();

    void saveAll(List<User> users);

    // Specific methods for each user type
    List<Student> findStudents();
    List<Teacher> findTeachers();
    List<Admin> findAdmins();
}
