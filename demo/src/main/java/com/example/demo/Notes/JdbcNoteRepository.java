package com.example.demo.Notes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcNoteRepository implements NoteRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Note> findById(Integer id) {
        String sql = "SELECT id, description, grade FROM note WHERE id = ?";
        Note note = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Note(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getFloat("grade")
                ));
        return Optional.ofNullable(note);
    }

    @Override
    public void create(Note note) {
        String sql = "INSERT INTO note (description, grade) VALUES (?, ?)";
        int updated = jdbcTemplate.update(sql, note.getDescription(), note.getGrade());
        Assert.state(updated == 1, "Failed to create note with description: " + note.getDescription());
    }

    @Override
    public void update(Note note, Integer id) {
        String sql = "UPDATE note SET description = ?, grade = ? WHERE id = ?";
        int updated = jdbcTemplate.update(sql, note.getDescription(), note.getGrade(), id);
        Assert.state(updated == 1, "Failed to update note with ID: " + id);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM note WHERE id = ?";
        int updated = jdbcTemplate.update(sql, id);
        Assert.state(updated == 1, "Failed to delete note with ID: " + id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM note";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<Note> findAll() {
        String sql = "SELECT id, description, grade FROM note";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Note(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getFloat("grade")
                ));
    }
}
