package com.example.demo.DueWorks;

import java.util.List;
import java.util.Optional;

public interface DueWorkRepository {
    Optional<DueWork> findById(int id);
    List<DueWork> findAll();
    void create(DueWork dueWork);
    void update(DueWork dueWork, int id);
    void delete(int id);
}
