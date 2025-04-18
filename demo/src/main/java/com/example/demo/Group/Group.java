package com.example.demo.Group;

import com.example.demo.subclasses.Student;
import com.example.demo.subclasses.Teacher;
import com.example.demo.Level.Level;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonIgnoreProperties({"groups", "students", "subjects"})
    private Level level;

    @ManyToMany
    @JoinTable(
        name = "group_teacher",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @JsonIgnoreProperties({"groups", "subjects"})
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "group")
    @JsonIgnoreProperties({"group", "level", "grades", "workReturns"})
    private Set<Student> students;  // Bidirectional relationship to Student

    // Constructors
    public Group() {
        this.teachers = new HashSet<>(); // Initialize teachers with a HashSet
    }

    public Group(String name, Level level) {
        this.name = name;
        this.level = level;
        this.teachers = new HashSet<>(); // Initialize teachers with a HashSet
    }

    // Constructor to accept name, level, and a teacher
    public Group(String name, Level level, Teacher teacher) {
        this.name = name;
        this.level = level;
        this.teachers = new HashSet<>(); // Initialize teachers with a HashSet
        this.teachers.add(teacher);  // Add the teacher to the set
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
