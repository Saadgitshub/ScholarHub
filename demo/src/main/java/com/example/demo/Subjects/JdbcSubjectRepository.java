package com.example.demo.Subjects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcSubjectRepository implements SubjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSubjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Subject> findById(Integer id) {
        String sql = "SELECT id, matiere FROM subject WHERE id = ?";
        Subject subject = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Subject(
                        rs.getInt("id"),
                        rs.getString("matiere")
                ));
        return Optional.ofNullable(subject);
    }

    @Override
    public Optional<Subject> findByMatiere(String matiere) {
        String sql = "SELECT id, matiere FROM subject WHERE matiere = ?";
        Subject subject = jdbcTemplate.queryForObject(sql, new Object[]{matiere}, (rs, rowNum) ->
                new Subject(
                        rs.getInt("id"),
                        rs.getString("matiere")
                ));
        return Optional.ofNullable(subject);
    }

    @Override
    public void create(Subject subject) {
        String sql = "INSERT INTO subject (matiere) VALUES (?)";
        int updated = jdbcTemplate.update(sql, subject.getMatiere());
        Assert.state(updated == 1, "Failed to create subject: " + subject.getMatiere());
    }

    @Override
    public void update(Subject subject, Integer id) {
        String sql = "UPDATE subject SET matiere = ? WHERE id = ?";
        int updated = jdbcTemplate.update(sql, subject.getMatiere(), id);
        Assert.state(updated == 1, "Failed to update subject: " + subject.getMatiere());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM subject WHERE id = ?";
        int updated = jdbcTemplate.update(sql, id);
        Assert.state(updated == 1, "Failed to delete subject with ID " + id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM subject";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void saveAll(List<Subject> subjects) {
        for (Subject subject : subjects) {
            create(subject);
        }
    }

    @Override
    public List<Subject> findAll() {
        String sql = "SELECT id, matiere FROM subject";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Subject(
                        rs.getInt("id"),
                        rs.getString("matiere")
                ));
    }
}
