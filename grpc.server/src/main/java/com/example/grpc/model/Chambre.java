package com.example.grpc.model;

import jakarta.persistence.*;


@Entity
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure proper ID generation strategy
    private Integer id;          // Unique identifier for the room

    @Column(name = "numero_etage", nullable = false)
    private int numeroEtage;     // Floor number of the room

    @Column(name = "price", nullable = false)
    private double price;        // Price of the room

    @Column(name = "number_of_bed", nullable = false)
    private int numberOfBed;     // Number of beds in the room

    @Column(name = "pic", length = 255, nullable = true)
    private String pic;          // Picture of the room (file path or URL)

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)  // Foreign key to Hotel
    private Hotel hotel;         // The hotel to which this room belongs

    // Default Constructor
    public Chambre() {
    }

    // Parameterized Constructor
    public Chambre(int numeroEtage, double price, int numberOfBed, String pic, Hotel hotel) {
        this.numeroEtage = numeroEtage;
        this.price = price;
        this.numberOfBed = numberOfBed;
        this.pic = pic;
        this.hotel = hotel;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroEtage() {
        return numeroEtage;
    }

    public void setNumeroEtage(int numeroEtage) {
        this.numeroEtage = numeroEtage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    // Override toString() for better readability
    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", numeroEtage=" + numeroEtage +
                ", price=" + price +
                ", numberOfBed=" + numberOfBed +
                ", pic='" + pic + '\'' +
                ", hotel=" + hotel.getName() +  // Display hotel name instead of full hotel details
                '}';
    }
}
