package com.example.demo.Groupes;
import java.util.List;
import java.util.Optional;

public interface GroupRepository {

    // Fetch a group by its ID
    Optional<Group> findById(Integer id);

    // Fetch a group by its name
    Optional<Group> findByName(String name);

    // Create a new group
    void create(Group group);

    // Update an existing group
    void update(Group group, Integer id);

    // Delete a group by its ID
    void delete(Integer id);

    // Count the total number of groups
    int count();

    // Save multiple groups at once
    void saveAll(List<Group> groups);

    // Retrieve all groups
    List<Group> findAll();
}
