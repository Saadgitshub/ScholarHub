package com.example.demo.subclasses;

import com.example.demo.User.User;
import com.example.demo.Group.Group;
import com.example.demo.Level.Level;
import com.example.demo.Grade.Grade;
import com.example.demo.WorkReturn.WorkReturn;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "students")
@JsonIgnoreProperties({
    "grades", "returns", "password"
})

public class Student extends User {

    private String gradeLevel;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnoreProperties({"students", "teachers", "level"})
    private Group group;

    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonIgnoreProperties({"students", "groups", "subjects"})
    private Level level;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"student", "subject"})
    private List<Grade> grades;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"student", "assignment"})
    private List<WorkReturn> returns;

    public Student() {}

    public Student(String username, String email, String password, String gradeLevel, Group group, Level level) {
        super(username, email, password);
        this.gradeLevel = gradeLevel;
        this.group = group;
        this.level = level;
    }

    // Getters and Setters
    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<WorkReturn> getReturns() {
        return returns;
    }

    public void setReturns(List<WorkReturn> returns) {
        this.returns = returns;
    }

    
}
