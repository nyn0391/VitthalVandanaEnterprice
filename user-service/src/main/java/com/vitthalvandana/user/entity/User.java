package com.vitthalvandana.user.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String firstName;

    @Column(nullable = false)
    public String lastName;

    @Column(nullable = false)
    public String passwordHash;

    @Column(nullable = false)
    public String phoneNumber;

    @Column(nullable = false)
    public String address;

    @Column(nullable = false)
    public String city;

    @Column(nullable = false)
    public String state;

    @Column(nullable = false)
    public String zipCode;

    @Column(nullable = false)
    public String country;

    @Column(nullable = false)
    public LocalDateTime createdAt;

    @Column(nullable = false)
    public LocalDateTime updatedAt;

    @Column(nullable = false)
    public Boolean isActive;

    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
    }

    public static User findByEmail(String email) {
        return find("email", email).firstResult();
    }
}