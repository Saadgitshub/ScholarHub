package com.example.demo.Notes;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryNoteRepository implements NoteRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryNoteRepository.class);
    private final List<Note> notes = new ArrayList<>();

    @Override
    public Optional<Note> findById(Integer id) {
        return notes.stream()
                .filter(note -> Objects.equals(note.getId(), id))
                .findFirst();
    }

    @Override
    public void create(Note note) {
        notes.add(note);
        log.info("Created new note with ID: " + note.getId());
    }

    @Override
    public void update(Note updatedNote, Integer id) {
        findById(id).ifPresent(existingNote -> {
            int index = notes.indexOf(existingNote);
            notes.set(index, updatedNote);
            log.info("Updated note with ID: " + id);
        });
    }

    @Override
    public void delete(Integer id) {
        notes.removeIf(note -> Objects.equals(note.getId(), id));
        log.info("Deleted note with ID: " + id);
    }

    @Override
    public int count() {
        return notes.size();
    }

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(notes);
    }

    @PostConstruct
    private void init() {
        log.info("InMemoryNoteRepository initialized.");
    }
}
