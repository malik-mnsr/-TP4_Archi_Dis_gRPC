package com.example.grpc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agencyId;
    private String username;
    private String password;
    private String name;

    // Default constructor (required for JPA)
    public Agency() {
    }

    // Constructor
    public Agency(int agencyId, String username, String password, String name) {
        this.agencyId = agencyId;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    // Getters and Setters
    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
