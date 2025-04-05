package com.example.demo.Levels;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcLevelRepository implements LevelRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLevelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Level> findById(Integer id) {
        String sql = "SELECT id, name FROM levels WHERE id = ?";
        Level level = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Level(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
        return Optional.ofNullable(level);
    }

    @Override
    public Optional<Level> findByName(String name) {
        String sql = "SELECT id, name FROM levels WHERE name = ?";
        Level level = jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) ->
                new Level(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
        return Optional.ofNullable(level);
    }

    @Override
    public void create(Level level) {
        String sql = "INSERT INTO levels (name) VALUES (?)";
        int updated = jdbcTemplate.update(sql, level.getName());
        Assert.state(updated == 1, "Failed to create level: " + level.getName());
    }

    @Override
    public void update(Level level, Integer id) {
        String sql = "UPDATE levels SET name = ? WHERE id = ?";
        int updated = jdbcTemplate.update(sql, level.getName(), id);
        Assert.state(updated == 1, "Failed to update level: " + level.getName());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM levels WHERE id = ?";
        int updated = jdbcTemplate.update(sql, id);
        Assert.state(updated == 1, "Failed to delete level with ID " + id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM levels";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void saveAll(List<Level> levels) {
        for (Level level : levels) {
            create(level);
        }
    }

    @Override
    public List<Level> findAll() {
        String sql = "SELECT id, name FROM levels";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Level(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }
}
