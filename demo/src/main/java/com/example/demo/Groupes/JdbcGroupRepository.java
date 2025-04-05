package com.example.demo.Groupes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcGroupRepository implements GroupRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGroupRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Group> findById(Integer id) {
        String sql = "SELECT id, name FROM groups WHERE id = ?";
        Group group = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Group(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
        return Optional.ofNullable(group);
    }

    @Override
    public Optional<Group> findByName(String name) {
        String sql = "SELECT id, name FROM groups WHERE name = ?";
        Group group = jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) ->
                new Group(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
        return Optional.ofNullable(group);
    }

    @Override
    public void create(Group group) {
        String sql = "INSERT INTO groups (name) VALUES (?)";
        int updated = jdbcTemplate.update(sql, group.getName());
        Assert.state(updated == 1, "Failed to create group: " + group.getName());
    }

    @Override
    public void update(Group group, Integer id) {
        String sql = "UPDATE groups SET name = ? WHERE id = ?";
        int updated = jdbcTemplate.update(sql, group.getName(), id);
        Assert.state(updated == 1, "Failed to update group: " + group.getName());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM groups WHERE id = ?";
        int updated = jdbcTemplate.update(sql, id);
        Assert.state(updated == 1, "Failed to delete group with ID " + id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM groups";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void saveAll(List<Group> groups) {
        for (Group group : groups) {
            create(group);
        }
    }

    @Override
    public List<Group> findAll() {
        String sql = "SELECT id, name FROM groups";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Group(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }
}
