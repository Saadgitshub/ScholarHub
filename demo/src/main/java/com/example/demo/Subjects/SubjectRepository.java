package com.example.demo.Subjects;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {

    Optional<Subject> findById(Integer id);

    Optional<Subject> findByMatiere(String matiere);

    void create(Subject subject);

    void update(Subject subject, Integer id);

    void delete(Integer id);

    int count();

    void saveAll(List<Subject> subjects);

    List<Subject> findAll();
}
