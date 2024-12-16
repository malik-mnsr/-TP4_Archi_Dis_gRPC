package com.example.grpc.model;

import jakarta.persistence.*;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                   // Unique identifier for the hotel

    @Column(name = "name", length = 100, nullable = false)
    private String name;                  // Name of the hotel

    @Column(name = "stars", nullable = false)
    private int stars;                    // Star rating of the hotel

    @Column(name = "number_of_beds", nullable = false)
    private int numberOfBeds;             // Number of beds in the hotel
    private Adresse adr;
    // Default Constructor
    public Hotel() {
    }
    public Hotel(String name, int stars, int numberOfBeds, Adresse adr) {
        this.name = name;
        this.stars = stars;
        this.numberOfBeds = numberOfBeds;
        this.adr=adr;
    }
    // Constructor with all fields (excluding price)
    public Hotel(String name, int stars, int numberOfBeds) {
        this.name = name;
        this.stars = stars;
        this.numberOfBeds = numberOfBeds;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Adresse getAdr() {
        return adr;
    }

    public void setAdr(Adresse adr) {
        this.adr = adr;
    }

    // Override toString() for easy printing
    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", numberOfBeds=" + numberOfBeds +
                '}';
    }
}