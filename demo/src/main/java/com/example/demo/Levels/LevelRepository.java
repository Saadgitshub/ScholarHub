package com.example.demo.Levels;

import java.util.List;
import java.util.Optional;

public interface LevelRepository {

    // Fetch a level by its ID
    Optional<Level> findById(Integer id);

    // Fetch a level by its name
    Optional<Level> findByName(String name);

    // Create a new level
    void create(Level level);

    // Update an existing level
    void update(Level level, Integer id);

    // Delete a level by its ID
    void delete(Integer id);

    // Count the total number of levels
    int count();

    // Save multiple levels at once
    void saveAll(List<Level> levels);

    // Retrieve all levels
    List<Level> findAll();
}
