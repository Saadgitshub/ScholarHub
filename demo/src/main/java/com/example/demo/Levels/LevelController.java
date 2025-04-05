package com.example.demo.Levels;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/levels")
class LevelController {

    private final LevelRepository levelRepository;

    LevelController(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    // ✅ Find level by ID
    @GetMapping("/{id}")
    Level findById(@PathVariable Integer id) {
        Optional<Level> level = levelRepository.findById(id);
        if (level.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found.");
        }
        return level.get();
    }

    // ✅ Create level
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Level level) {
        levelRepository.create(level);
    }

    // ✅ Update level
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Level level, @PathVariable Integer id) {
        Optional<Level> existingLevel = levelRepository.findById(id);
        if (existingLevel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Level with id " + id + " not found.");
        }
        levelRepository.update(level, id);
    }

    // ✅ Delete level
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        Optional<Level> existingLevel = levelRepository.findById(id);
        if (existingLevel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Level with id " + id + " not found.");
        }
        levelRepository.delete(id);
    }

    // ✅ Fetch all levels
    @GetMapping
    List<Level> findAll() {
        return levelRepository.findAll();
    }
}
