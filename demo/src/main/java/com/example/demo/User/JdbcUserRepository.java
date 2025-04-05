package com.example.demo.User;

import com.example.demo.subclasses.*;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT id, name, username, email, password FROM users WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            if (rs.getString("email").contains("student")) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else if (rs.getString("email").contains("teacher")) {
                return new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else if (rs.getString("email").contains("admin")) {
                return new Admin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else {
                return null;
            }
        });
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT id, name, username, email, password FROM users WHERE username = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
            if (rs.getString("email").contains("student")) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else if (rs.getString("email").contains("teacher")) {
                return new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else if (rs.getString("email").contains("admin")) {
                return new Admin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else {
                return null;
            }
        });
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id, name, username, email, password FROM users WHERE email = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
            if (rs.getString("email").contains("student")) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else if (rs.getString("email").contains("teacher")) {
                return new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else if (rs.getString("email").contains("admin")) {
                return new Admin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else {
                return null;
            }
        });
        return Optional.ofNullable(user);
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (name, username, email, password) VALUES (?, ?, ?, ?)";
        int updated = jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getEmail(), user.getPassword());
        Assert.state(updated == 1, "Failed to create user " + user.getUsername());
    }

    @Override
    public void update(User user, Integer id) {
        String sql = "UPDATE users SET name = ?, username = ?, email = ?, password = ? WHERE id = ?";
        int updated = jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), id);
        Assert.state(updated == 1, "Failed to update user " + user.getUsername());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int updated = jdbcTemplate.update(sql, id);
        Assert.state(updated == 1, "Failed to delete user " + id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void saveAll(List<User> users) {
        for (User user : users) {
            create(user);
        }
    }

    @Override
    public List<Student> findStudents() {
        String sql = "SELECT id, name, username, email, password FROM users WHERE email LIKE '%student%'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        ));
    }

    @Override
    public List<Teacher> findTeachers() {
        String sql = "SELECT id, name, username, email, password FROM users WHERE email LIKE '%teacher%'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Teacher(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        ));
    }

    @Override
    public List<Admin> findAdmins() {
        String sql = "SELECT id, name, username, email, password FROM users WHERE email LIKE '%admin%'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Admin(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        ));
    }
}
