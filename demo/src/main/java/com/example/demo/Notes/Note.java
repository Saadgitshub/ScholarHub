package com.example.demo.Notes;

public class Note {
    private Integer id;
    private String description;
    private float grade;

    public Note(Integer id, String description, float grade) {
        this.id = id;
        this.description = description;
        this.grade = grade;
    }

    public Note() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", grade=" + grade +
                '}';
    }
}
