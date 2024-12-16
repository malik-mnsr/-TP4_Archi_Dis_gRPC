package com.example.grpc.client.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                  // Unique identifier for the offer

    @Column(name = "new_price", nullable = false)
    private double newPrice;             // New price for the offer

    @Column(name = "availability_start", nullable = false)
    private Date availabilityStart;      // Start date of availability for the offer

    @Column(name = "availability_end", nullable = false)
    private Date availabilityEnd;        // End date of availability for the offer

    @Column(name = "number_of_beds", nullable = false)
    private int numberOfBeds;            // Number of beds in the offer

    @Column(name = "agency_username", length = 100, nullable = true)
    private String agencyUsername;       // Username for the agency

    @Column(name = "agency_password", length = 100, nullable = true)
    private String agencyPassword;       // Password for the agency

    @Column(name = "agency_id", nullable = false)
    private int agencyId;                // ID of the agency

    @Column(name = "picture_url", length = 255, nullable = true)
    private String pictureUrl;           // URL for the room picture

    // Assuming you have a Chambre entity that represents a room
   // @ManyToOne
    //@JoinColumn(name = "chambre_id", nullable = false) // Foreign key to Chambre
   // private Chambre chambre;              // Reference to the associated room

    // Default Constructor
    public Offer() {
    }

    // Constructor with all fields, except for the chambre object
    public Offer(double newPrice, Date availabilityStart, Date availabilityEnd, int numberOfBeds, int agencyId, String pictureUrl /*Chambre chambre */) {
        this.newPrice = newPrice;
        this.availabilityStart = availabilityStart;
        this.availabilityEnd = availabilityEnd;
        this.numberOfBeds = numberOfBeds;
        this.agencyId = agencyId;
        this.pictureUrl = pictureUrl;
      //  this.chambre = chambre;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public Date getAvailabilityStart() {
        return availabilityStart;
    }

    public void setAvailabilityStart(Date availabilityStart) {
        this.availabilityStart = availabilityStart;
    }

    public Date getAvailabilityEnd() {
        return availabilityEnd;
    }

    public void setAvailabilityEnd(Date availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getAgencyUsername() {
        return agencyUsername;
    }

    public void setAgencyUsername(String agencyUsername) {
        this.agencyUsername = agencyUsername;
    }

    public String getAgencyPassword() {
        return agencyPassword;
    }

    public void setAgencyPassword(String agencyPassword) {
        this.agencyPassword = agencyPassword;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

   // public Chambre getChambre() {
     //   return chambre;
    //}

    //public void setChambre(Chambre chambre) {
     //   this.chambre = chambre;
    //}

    // Override toString() for easy printing
    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", newPrice=" + newPrice +
                ", availabilityStart=" + availabilityStart +
                ", availabilityEnd=" + availabilityEnd +
                ", numberOfBeds=" + numberOfBeds +
                ", pictureUrl='" + pictureUrl + '\'' +
      //          ", chambre=" + chambre +
                '}';
    }
}
