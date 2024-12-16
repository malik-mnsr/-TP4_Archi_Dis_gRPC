package com.example.grpc.repository;


import com.example.grpc.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelAvailabilityService extends JpaRepository<Offer, Integer> {
}
