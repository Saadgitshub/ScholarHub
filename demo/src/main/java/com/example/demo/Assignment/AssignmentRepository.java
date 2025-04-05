package com.example.demo.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository {

    List<Assignment> findAll();

    Optional<Assignment> findById(Integer id);

    void create(Assignment assignment);

    void update(Assignment assignment, Integer id);

    void delete(Integer id);

    int count();

    void saveAll(List<Assignment> assignments);

    List<Assignment> findByTeacher(Integer teacherId);
}
