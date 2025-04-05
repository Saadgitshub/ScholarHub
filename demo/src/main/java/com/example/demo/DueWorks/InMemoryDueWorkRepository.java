package com.example.demo.DueWorks;

import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryDueWorkRepository implements DueWorkRepository {

    private final Map<Integer, DueWork> database = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger();

    @Override
    public Optional<DueWork> findById(int id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<DueWork> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void create(DueWork dueWork) {
        int id = idGenerator.incrementAndGet();
        dueWork.setId(id);
        database.put(id, dueWork);
    }

    @Override
    public void update(DueWork dueWork, int id) {
        dueWork.setId(id);
        database.put(id, dueWork);
    }

    @Override
    public void delete(int id) {
        database.remove(id);
    }
}
