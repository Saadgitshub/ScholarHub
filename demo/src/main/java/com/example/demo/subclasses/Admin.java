package com.example.demo.subclasses;
import com.example.demo.User.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Table(name = "admins") 
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)

public class Admin extends User {

    public Admin() {}

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }


}
