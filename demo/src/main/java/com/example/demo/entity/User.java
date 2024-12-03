package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "First name is required")
    @Length(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Length(max = 50)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Length(max = 100)
    private String timeZone;

    @NotNull
    private Boolean isActive;

    public User() {}

    public User(String firstName, String lastName, String timeZone, Boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeZone = timeZone;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
