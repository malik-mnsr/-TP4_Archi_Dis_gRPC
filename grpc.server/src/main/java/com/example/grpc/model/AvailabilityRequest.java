package com.example.grpc.model;

import java.time.LocalDate;
import java.util.Objects;

public class AvailabilityRequest {
    private String agencyId;
    private String password;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfPeople;




    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public String toString() {
        return "AvailabilityRequest{" +
                "agencyId='" + agencyId + '\'' +
                ", password='" + password + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfPeople=" + numberOfPeople +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityRequest that = (AvailabilityRequest) o;
        return numberOfPeople == that.numberOfPeople && Objects.equals(agencyId, that.agencyId) && Objects.equals(password, that.password) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencyId, password, startDate, endDate, numberOfPeople);
    }
}
