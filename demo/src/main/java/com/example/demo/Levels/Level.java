package com.example.demo.Levels;

import com.example.demo.Groupes.Group;
import java.util.List;

public class Level {
    private Integer id;
    private String name;
    private List<Group> groups; // List of associated groups

    public Level(Integer id, String name, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.groups = groups;
    }

    public Level() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
