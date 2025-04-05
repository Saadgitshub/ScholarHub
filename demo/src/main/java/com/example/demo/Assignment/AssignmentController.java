package com.example.demo.Assignment;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
class AssignmentController {

    private final AssignmentRepository assignmentRepository;

    AssignmentController(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @GetMapping
    List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    @GetMapping("/{id}")
    Assignment findById(@PathVariable Integer id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        if (assignment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found.");
        }
        return assignment.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Assignment assignment) {
        assignmentRepository.create(assignment);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Assignment assignment, @PathVariable Integer id) {
        assignmentRepository.update(assignment, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        assignmentRepository.delete(id);
    }

    @GetMapping("/teacher/{teacherId}")
    List<Assignment> findByTeacher(@PathVariable Integer teacherId) {
        return assignmentRepository.findByTeacher(teacherId);
    }
}
