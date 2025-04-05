package com.example.demo.Levels;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryLevelRepository implements LevelRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryLevelRepository.class);
    private final List<Level> levels = new ArrayList<>();

    @Override
    public Optional<Level> findById(Integer id) {
        return levels.stream()
                .filter(level -> Objects.equals(level.getId(), id))
                .findFirst();
    }

    @Override
    public void create(Level level) {
        levels.add(level);
        log.info("Created level: " + level.getName());
    }

    @Override
    public void update(Level newLevel, Integer id) {
        findById(id).ifPresent(existingLevel -> {
            int index = levels.indexOf(existingLevel);
            levels.set(index, newLevel);
            log.info("Updated level with ID: " + id);
        });
    }

    @Override
    public void delete(Integer id) {
        levels.removeIf(level -> Objects.equals(level.getId(), id));
        log.info("Deleted level with ID: " + id);
    }

    @Override
    public int count() {
        return levels.size();
    }

    @Override
    public void saveAll(List<Level> levels) {
        this.levels.addAll(levels);
        log.info("Saved " + levels.size() + " levels.");
    }

    @Override
    public List<Level> findAll() {
        return new ArrayList<>(levels);
    }

    @Override
    public List<Group> findGroupsByLevel(Integer levelId) {
        List<Group> result = new ArrayList<>();

        // Iterate over all levels
        for (Level level : levels) {
            // Check if the level id matches
            if (Objects.equals(level.getId(), levelId)) {
                // Add the groups of the matched level to the result list
                result.addAll(level.getGroups());
            }
        }

        return result;
    }
    @PostConstruct
    private void init() {
        log.info("InMemoryLevelRepository initialized.");
    }
}
