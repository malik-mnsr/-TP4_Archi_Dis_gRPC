package com.example.grpc.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Adresse {

    @Column(name = "country", nullable = false, length = 100)
    private String country;      // Country of the address

    @Column(name = "city", nullable = false, length = 100)
    private String city;         // City of the address

    @Column(name = "street", nullable = true, length = 255)
    private String street;       // Street of the address

    @Column(name = "positionGPS", nullable = true, length = 255)
    private String positionGPS;  // GPS position of the address

    // Default constructor
    public Adresse() {}

    // Parameterized constructor
    public Adresse(String country, String city, String street, String positionGPS) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.positionGPS = positionGPS;
    }

    // Getters and Setters
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPositionGPS() {
        return positionGPS;
    }

    public void setPositionGPS(String positionGPS) {
        this.positionGPS = positionGPS;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return Objects.equals(country, adresse.country) &&
                Objects.equals(city, adresse.city) &&
                Objects.equals(street, adresse.street) &&
                Objects.equals(positionGPS, adresse.positionGPS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, positionGPS);
    }

    // Override toString() for easy printing
    @Override
    public String toString() {
        return "Adresse{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", positionGPS='" + positionGPS + '\'' +
                '}';
    }
}
