package com.example.demo.Subjects;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemorySubjectRepository implements SubjectRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemorySubjectRepository.class);
    private final List<Subject> subjects = new ArrayList<>();

    @Override
    public Optional<Subject> findById(Integer id) {
        return subjects.stream()
                .filter(subject -> Objects.equals(subject.getId(), id))
                .findFirst();
    }

    @Override
    public Optional<Subject> findByMatiere(String matiere) {
        return subjects.stream()
                .filter(subject -> Objects.equals(subject.getMatiere(), matiere))
                .findFirst();
    }

    @Override
    public void create(Subject subject) {
        subjects.add(subject);
        log.info("Created subject: {}", subject.getMatiere());
    }

    @Override
    public void update(Subject newSubject, Integer id) {
        findById(id).ifPresent(existingSubject -> {
            int index = subjects.indexOf(existingSubject);
            subjects.set(index, newSubject);
            log.info("Updated subject with ID: {}", id);
        });
    }

    @Override
    public void delete(Integer id) {
        subjects.removeIf(subject -> Objects.equals(subject.getId(), id));
        log.info("Deleted subject with ID: {}", id);
    }

    @Override
    public int count() {
        return subjects.size();
    }

    @Override
    public void saveAll(List<Subject> subjects) {
        this.subjects.addAll(subjects);
        log.info("Saved {} subjects", subjects.size());
    }

    @Override
    public List<Subject> findAll() {
        return new ArrayList<>(subjects);
    }

    @PostConstruct
    private void init() {
        log.info("InMemorySubjectRepository initialized.");
    }
}
