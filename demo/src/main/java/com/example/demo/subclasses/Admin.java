package com.example.demo.subclasses;
import com.example.demo.User.*;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")  // Define the discriminator value for Admin
public class Admin extends User {

    public Admin(Integer id, String name, String username, String email, String password) {
        super(id, name, username, email, password);
    }

    public Admin() {
        super();
    }
}
