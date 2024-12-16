package com.example.grpc.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int agencyId;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date reservationDate;
    private int offerId;
    private int chambreId;
    private String hotelId;
    // Date formatter for parsing and formatting dates in YYYY-MM-DD format
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor with Date for better date handling
    public Reservation(String name, Date startDate, Date endDate, int offerId, String hotelId) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.offerId = offerId;
        this.chambreId = chambreId;
        this.reservationDate = new Date(System.currentTimeMillis());  // Set the reservation date to the current date
    }

    // Constructor with String input for dates, to handle String input
    public Reservation(String name, String startDateStr, String endDateStr, int offerId, int chambreId) {
        try {
            this.name = name;
            this.startDate = Date.valueOf(startDateStr);
            this.endDate = Date.valueOf(endDateStr);
            this.offerId = offerId;
            this.chambreId = chambreId;
            this.reservationDate = new Date(System.currentTimeMillis());  // Set the reservation date to the current date
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Handle invalid date format
        }
    }

    public Reservation() {

    }

    // Method to calculate total price based on the offer's price and the number of nights
    public double calculateTotalPrice(double offerPrice) {
        long diffInMillies = endDate.getTime() - startDate.getTime();
        long nights = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return offerPrice * nights;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getChambreId() {
        return chambreId;
    }

    public void setChambreId(int chambreId) {
        this.chambreId = chambreId;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    // Method for reservation confirmation
    public String getConfirmation(double offerPrice) {
        // Calculate the total price for the reservation
        double totalPrice = calculateTotalPrice(offerPrice);

        // Generate the confirmation message using StringBuilder for better performance
        StringBuilder confirmation = new StringBuilder();
        confirmation.append("Reservation Confirmation: \n")
                .append("Reservation ID: ").append(id).append("\n")
                .append("Reservation Date: ").append(DATE_FORMATTER.format(reservationDate)).append("\n")  // Show reservation date
                .append("Name: ").append(name).append("\n")
                .append("Check-in Date: ").append(DATE_FORMATTER.format(startDate)).append("\n")
                .append("Check-out Date: ").append(DATE_FORMATTER.format(endDate)).append("\n")
                .append("Offer ID: ").append(offerId).append("\n")
                .append("Room ID: ").append(chambreId).append("\n")
                .append("Total Price: ").append(totalPrice);

        return confirmation.toString();
    }

    // Override toString() for easy printing
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + DATE_FORMATTER.format(startDate) +
                ", endDate=" + DATE_FORMATTER.format(endDate) +
                ", reservationDate=" + DATE_FORMATTER.format(reservationDate) +
                ", offerId=" + offerId +
                ", chambreId=" + chambreId +
                '}';
    }
}
