package com.example.demo.Groupes;

import com.example.demo.Levels.Level;

public class Group {
    private Integer id;
    private String name;
    private Level level; // Reference to Level (Niveau)

    public Group(Integer id, String name, Level level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Group() {}

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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
