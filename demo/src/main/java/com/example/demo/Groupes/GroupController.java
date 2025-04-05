package com.example.demo.Groupes;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
class GroupController {

    private final GroupRepository groupRepository;

    GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    // ✅ Find group by ID
    @GetMapping("/{id}")
    Group findById(@PathVariable Integer id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
        }
        return group.get();
    }

    // ✅ Create group
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Group group) {
        groupRepository.create(group);
    }

    // ✅ Update group
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Group group, @PathVariable Integer id) {
        Optional<Group> existingGroup = groupRepository.findById(id);
        if (existingGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id " + id + " not found.");
        }
        groupRepository.update(group, id);
    }

    // ✅ Delete group
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        Optional<Group> existingGroup = groupRepository.findById(id);
        if (existingGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id " + id + " not found.");
        }
        groupRepository.delete(id);
    }

    // ✅ Fetch all groups
    @GetMapping
    List<Group> findAll() {
        return groupRepository.findAll();
    }
}
