package com.example.demo.Assignment;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcAssignmentRepository implements AssignmentRepository {

    private final JdbcClient jdbcClient;

    public JdbcAssignmentRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // Find all assignments
    public List<Assignment> findAll() {
        return jdbcClient.sql("SELECT * FROM Assignments")
                .query(Assignment.class)
                .list();
    }

    // Find an assignment by its ID
    public Optional<Assignment> findById(Integer id) {
        return jdbcClient.sql("SELECT id, title, description, dueDate, teacherId FROM Assignments WHERE id = :id")
                .param("id", id)
                .query(Assignment.class)
                .optional();
    }

    // Create a new assignment
    public void create(Assignment assignment) {
        try {
            // Insert assignment into the database, with the teacherId as the foreign key
            var updated = jdbcClient.sql("INSERT INTO Assignments (title, description, dueDate, teacherId) VALUES (?, ?, ?, ?)")
                    .params(List.of(assignment.title(), assignment.description(), assignment.dueDate(), assignment.teacherId()))
                    .update();
    
            Assert.state(updated == 1, "Failed to create assignment " + assignment.title());
        } catch (DataAccessException e) {
            System.out.println("DataAccess Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update an existing assignment
    public void update(Assignment assignment, Integer id) {
        var updated = jdbcClient.sql("UPDATE Assignments SET title = ?, description = ?, dueDate = ?, teacherId = ? WHERE id = ?")
                .params(List.of(assignment.title(), assignment.description(), assignment.dueDate(), assignment.teacherId(), id))
                .update();

        Assert.state(updated == 1, "Failed to update assignment " + assignment.title());
    }

    // Delete an assignment by ID
    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Assignments WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete assignment with ID " + id);
    }

    // Get the count of assignments
    public int count() {
        return jdbcClient.sql("SELECT * FROM Assignments").query().listOfRows().size();
    }

    // Save a list of assignments
    public void saveAll(List<Assignment> assignments) {
        assignments.forEach(this::create);
    }

    // Find assignments by a specific teacherId (foreign key reference to Users table)
    public List<Assignment> findByTeacher(Integer teacherId) {
        return jdbcClient.sql("SELECT * FROM Assignments WHERE teacherId = :teacherId")
                .param("teacherId", teacherId)
                .query(Assignment.class)
                .list();
    }
}
