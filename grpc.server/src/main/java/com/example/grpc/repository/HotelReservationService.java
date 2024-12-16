package com.example.grpc.repository;

import com.example.grpc.model.Offer;
import com.example.grpc.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelReservationService extends JpaRepository<Reservation, Integer> {
}

