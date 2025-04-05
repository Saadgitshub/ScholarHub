package com.example.demo.Notes;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    // Find a note by its ID
    Optional<Note> findById(Integer id);

    // Create a new note
    void create(Note note);

    // Update an existing note by ID
    void update(Note note, Integer id);

    // Delete a note by its ID
    void delete(Integer id);

    // Count the total number of notes
    int count();

    // Retrieve all notes
    List<Note> findAll();
}
