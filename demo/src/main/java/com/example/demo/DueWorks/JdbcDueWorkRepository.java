package com.example.demo.DueWorks;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JdbcDueWorkRepository implements DueWorkRepository {

    private final JdbcTemplate jdbc;

    public JdbcDueWorkRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<DueWork> rowMapper = (rs, rowNum) -> new DueWork(
            rs.getInt("id"),
            Arrays.asList(rs.getString("file_paths").split(","))
    );

    @Override
    public Optional<DueWork> findById(int id) {
        List<DueWork> results = jdbc.query("SELECT * FROM due_works WHERE id = ?", rowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public List<DueWork> findAll() {
        return jdbc.query("SELECT * FROM due_works", rowMapper);
    }

    @Override
    public void create(DueWork dueWork) {
        String joinedPaths = String.join(",", dueWork.getFilePaths());
        jdbc.update("INSERT INTO due_works (file_paths) VALUES (?)", joinedPaths);
    }

    @Override
    public void update(DueWork dueWork, int id) {
        String joinedPaths = String.join(",", dueWork.getFilePaths());
        jdbc.update("UPDATE due_works SET file_paths = ? WHERE id = ?", joinedPaths, id);
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM due_works WHERE id = ?", id);
    }
}
