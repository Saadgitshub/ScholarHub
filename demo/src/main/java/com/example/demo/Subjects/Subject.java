package com.example.demo.Subjects;

import jakarta.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String matiere;

    // Constructors
    public Subject() {}

    public Subject(Integer id, String matiere) {
        this.id = id;
        this.matiere = matiere;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
}
