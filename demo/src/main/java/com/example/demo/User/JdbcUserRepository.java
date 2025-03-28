package com.example.demo.User;


import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.dao.DataAccessException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcUserRepository implements UserRepository {

    private final JdbcClient jdbcClient;

    public JdbcUserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<User> findAll() {
        return jdbcClient.sql("SELECT * FROM users")
                .query(User.class)
                .list();
    }

    public Optional<User> findById(Integer id) {
        return jdbcClient.sql("SELECT id, name, username, email, password, role FROM users WHERE id = :id")
                .param("id", id)
                .query(User.class)
                .optional();
    }

    

    public void create(User user) {
        try {
            var updated = jdbcClient.sql("INSERT INTO users (name, username, email, password, role) VALUES (?, ?, ?, ?, ?)") // Omit 'id'
            .params(List.of(user.name(), user.username(), user.email(), user.password(), user.role().toString())) // Omit 'id'
            .update();
    
            Assert.state(updated == 1, "Failed to create user " + user.username());
        } catch (DataAccessException e) {
            System.out.println("DataAccess Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void update(User user, Integer id) {
        var updated = jdbcClient.sql("UPDATE users SET name = ?, username = ?, email = ?, password = ?, role = ? WHERE id = ?")
                .params(List.of(user.name(), user.username(), user.email(), user.password(), user.role().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update user " + user.username());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM users WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete user " + id);
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM users").query().listOfRows().size();
    }

    public void saveAll(List<User> users) {
        users.forEach(this::create);
    }

    public List<User> findByRole(Role role) {
        return jdbcClient.sql("SELECT * FROM users WHERE role = :role")
                .param("role", role.toString())
                .query(User.class)
                .list();
    }
}
