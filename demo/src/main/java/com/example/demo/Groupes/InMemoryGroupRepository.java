package com.example.demo.Groupes;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryGroupRepository implements GroupRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryGroupRepository.class);
    private final List<Group> groups = new ArrayList<>();

    @Override
    public Optional<Group> findById(Integer id) {
        return groups.stream()
                .filter(group -> Objects.equals(group.getId(), id))
                .findFirst();
    }

    @Override
    public Optional<Group> findByName(String name) {
        return groups.stream()
                .filter(group -> Objects.equals(group.getName(), name))
                .findFirst();
    }

    @Override
    public void create(Group group) {
        groups.add(group);
        log.info("Created group: " + group.getName() + " for level: " + group.getLevel().getName());
    }

    @Override
    public void update(Group newGroup, Integer id) {
        findById(id).ifPresent(existingGroup -> {
            int index = groups.indexOf(existingGroup);
            groups.set(index, newGroup);
            log.info("Updated group with ID: " + id);
        });
    }

    @Override
    public void delete(Integer id) {
        groups.removeIf(group -> Objects.equals(group.getId(), id));
        log.info("Deleted group with ID: " + id);
    }

    @Override
    public int count() {
        return groups.size();
    }

    @Override
    public void saveAll(List<Group> groups) {
        this.groups.addAll(groups);
        log.info("Saved " + groups.size() + " groups.");
    }

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(groups);
    }

    @PostConstruct
    private void init() {
        log.info("InMemoryGroupRepository initialized.");
    }
}
