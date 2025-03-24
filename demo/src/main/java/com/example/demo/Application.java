package com.example.demo;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.User.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Autowired
private JdbcTemplate jdbcTemplate;

	@Bean
CommandLineRunner initDatabase(JdbcUserRepository userRepository) {
    return args -> {
        // Check if the repository is correctly injected
        System.out.println("UserRepository: " + userRepository);

        // Attempt to check the database connection
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            // Execute a simple query to check the connection
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1");
            
            if (resultSet.next()) {
                System.out.println("Database connection is successful.");
            } else {
                System.err.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }

        // Continue with your existing user creation logic
        User user1 = new User(null, "John Doe", "johndoe", "john@example.com", "password123", Role.STUDENT);
        User user2 = new User(null, "Jane Smith", "janesmith", "jane@example.com", "password456", Role.TEACHER);

        try {
            userRepository.create(user1);
            userRepository.create(user2);
            System.out.println("Users added to database.");
        } catch (Exception e) {
            System.err.println("Error adding users: " + e.getMessage());
        }

        // Retrieve and print all users to verify
        userRepository.findAll().forEach(user -> System.out.println("User: " + user));
    };
}

}
