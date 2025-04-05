package com.example.demo.Subjects;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
class SubjectController {

    private final SubjectRepository subjectRepository;

    SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // ✅ Find subject by ID
    @GetMapping("/{id}")
    Subject findById(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found.");
        }
        return subject.get();
    }

    // ✅ Find subject by name (matiere)
    @GetMapping("/matiere/{matiere}")
    Subject findByMatiere(@PathVariable String matiere) {
        Optional<Subject> subject = subjectRepository.findByMatiere(matiere);
        if (subject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with matiere " + matiere + " not found.");
        }
        return subject.get();
    }

    // ✅ Create subject
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Subject subject) {
        subjectRepository.create(subject);
    }

    // ✅ Update subject
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Subject subject, @PathVariable Integer id) {
        Optional<Subject> existingSubject = subjectRepository.findById(id);
        if (existingSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with id " + id + " not found.");
        }
        subjectRepository.update(subject, id);
    }

    // ✅ Delete subject
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        Optional<Subject> existingSubject = subjectRepository.findById(id);
        if (existingSubject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with id " + id + " not found.");
        }
        subjectRepository.delete(id);
    }

    // ✅ Fetch all subjects
    @GetMapping
    List<Subject> findAll() {
        return subjectRepository.findAll();
    }
}
