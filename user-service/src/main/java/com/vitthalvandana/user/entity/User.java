package com.vitthalvandana.user.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String firstName;

    @Column(nullable = false)
    public String lastName;

    @Column(nullable = false, unique = true)
    public String phoneNumber;

    @Column(nullable = false)
    public String role = "USER";

    @Column(nullable = false)
    public Boolean active = true;

    @Column(name = "created_at")
    public LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    public LocalDateTime updatedAt = LocalDateTime.now();

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}