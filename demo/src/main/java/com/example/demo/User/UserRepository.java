package com.example.demo.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Integer id);

    void create(User user);

    void update(User user, Integer id);

    void delete(Integer id);

    int count();

    void saveAll(List<User> users);

    List<User> findByRole(Role role);
}
