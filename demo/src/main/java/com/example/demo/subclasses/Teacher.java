package com.example.demo.subclasses;

import com.example.demo.User.User;
import com.example.demo.Subject.Subject;
import com.example.demo.Group.Group;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "teachers")
@JsonIgnoreProperties({
    "password", "email", "username", "groups", "subjects"
})
public class Teacher extends User {

    @ManyToMany
    @JoinTable(
        name = "teacher_subject",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    @JsonIgnoreProperties({"teachers", "level", "grades", "assignments"})
    private List<Subject> subjects;

    @ManyToMany
    @JoinTable(
        name = "teacher_group",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    @JsonIgnoreProperties({"teachers", "students", "level"})
    private List<Group> groups;

    public Teacher() {}

    public Teacher(String username, String email, String password) {
        super(username, email, password);
    }

    // Getters and Setters
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
