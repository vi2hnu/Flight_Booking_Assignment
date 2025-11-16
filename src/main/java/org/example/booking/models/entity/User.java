package org.example.booking.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.example.booking.models.enums.Gender;

@Entity
public class User {
    @Id
    long id;
    String name;
    String email;
    @Enumerated(EnumType.STRING)
    Gender gender;



    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }
}
