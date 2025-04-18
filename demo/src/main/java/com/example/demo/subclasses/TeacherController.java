package com.example.demo.subclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    // Get all teachers
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Get a specific teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new teacher
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    // Update an existing teacher
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher teacherDetails) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        
        if (!teacherOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Teacher teacher = teacherOptional.get();
        teacher.setUsername(teacherDetails.getUsername());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setPassword(teacherDetails.getPassword());
        teacher.setSubjects(teacherDetails.getSubjects());
        teacher.setGroups(teacherDetails.getGroups());
        
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    // Delete a teacher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        
        if (!teacherOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        teacherRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
