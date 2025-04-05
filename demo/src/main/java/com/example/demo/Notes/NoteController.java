package com.example.demo.Notes;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
class NoteController {

    private final NoteRepository noteRepository;

    NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // ✅ Find note by ID
    @GetMapping("/{id}")
    Note findById(@PathVariable Integer id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found.");
        }
        return note.get();
    }

    // ✅ Create note
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Note note) {
        noteRepository.create(note);
    }

    // ✅ Update note
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Note note, @PathVariable Integer id) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + id + " not found.");
        }
        noteRepository.update(note, id);
    }

    // ✅ Delete note
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + id + " not found.");
        }
        noteRepository.delete(id);
    }

    // ✅ Fetch all notes
    @GetMapping
    List<Note> findAll() {
        return noteRepository.findAll();
    }
}
