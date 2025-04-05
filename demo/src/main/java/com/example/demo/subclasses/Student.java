package com.example.demo.subclasses;
import com.example.demo.User.*;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("STUDENT")  // Define the discriminator value for Student
public class Student extends User {

    public Student(Integer id, String name, String username, String email, String password) {
        super(id, name, username, email, password);
    }

    public Student() {
        super();
    }
}
