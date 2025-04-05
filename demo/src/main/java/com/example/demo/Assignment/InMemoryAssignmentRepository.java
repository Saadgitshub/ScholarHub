package com.example.demo.Assignment;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository

class InMemoryAssignmentRepository implements AssignmentRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryAssignmentRepository.class);
    private final List<Assignment> assignments = new ArrayList<>();

    public List<Assignment> findAll() {
        return assignments;
    }

    public Optional<Assignment> findById(Integer id) {
        return Optional.ofNullable(assignments.stream()
                .filter(assignment -> assignment.id().equals(id))
                .findFirst()
                .orElseThrow(AssignmentNotFoundException::new));
    }

    @Override
    public void create(Assignment assignment) {
        // Assume you're using some logic to get the current time, or a date set by the teacher
        LocalDateTime dueDate = LocalDateTime.of(2025, 5, 25, 14, 0); // Example date
    
        // Create a new assignment with the given dueDate
        Assignment newAssignment = new Assignment(
            assignment.id(), 
            assignment.title(), 
            assignment.description(), 
            dueDate,  // Set the dueDate
            assignment.teacherId()
        );
    
        assignments.add(newAssignment); // Add to the list
    }
    

    public void update(Assignment newAssignment, Integer id) {
        Optional<Assignment> existingAssignment = findById(id);
        if (existingAssignment.isPresent()) {
            var assignment = existingAssignment.get();
            log.info("Updating Existing Assignment: " + assignment);
            assignments.set(assignments.indexOf(assignment), newAssignment);
        }
    }

    public void delete(Integer id) {
        log.info("Deleting Assignment: " + id);
        assignments.removeIf(assignment -> assignment.id().equals(id));
    }

    public int count() {
        return assignments.size();
    }

    public void saveAll(List<Assignment> assignments) {
        assignments.forEach(this::create);
    }

    public List<Assignment> findByTeacher(Integer teacherId) {
        return assignments.stream()
                .filter(assignment -> Objects.equals(assignment.teacherId(), teacherId))
                .toList();
    }

    @PostConstruct
    private void init() {
        // Example data for testing
        assignments.add(new Assignment(1, "Math Homework", "Algebra equations", LocalDateTime.now(), 101));
        assignments.add(new Assignment(2, "Science Project", "Physics experiment", LocalDateTime.of(2024, 5, 20, 23, 59), 102));
    }
}
