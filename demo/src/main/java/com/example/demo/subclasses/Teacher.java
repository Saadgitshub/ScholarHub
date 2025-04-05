package com.example.demo.subclasses;
import com.example.demo.User.*;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")  // Define the discriminator value for Teacher
public class Teacher extends User {

    public Teacher(Integer id, String name, String username, String email, String password) {
        super(id, name, username, email, password);
    }

    public Teacher() {
        super();
    }
}
