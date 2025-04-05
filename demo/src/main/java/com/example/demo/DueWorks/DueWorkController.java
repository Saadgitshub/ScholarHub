package com.example.demo.DueWorks;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dueworks")
public class DueWorkController {

    private final DueWorkRepository dueWorkRepository;

    public DueWorkController(DueWorkRepository dueWorkRepository) {
        this.dueWorkRepository = dueWorkRepository;
    }

    @GetMapping("/{id}")
    public DueWork findById(@PathVariable int id) {
        return dueWorkRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Due work not found.")
        );
    }

    @GetMapping
    public List<DueWork> findAll() {
        return dueWorkRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody DueWork dueWork) {
        dueWorkRepository.create(dueWork);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody DueWork dueWork, @PathVariable int id) {
        if (dueWorkRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Due work not found.");
        }
        dueWorkRepository.update(dueWork, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if (dueWorkRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Due work not found.");
        }
        dueWorkRepository.delete(id);
    }
}
