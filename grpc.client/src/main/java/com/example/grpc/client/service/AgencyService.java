package com.example.grpc.client.service;

import availability.HotelAvailabilityServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import reservation.ReservationServiceGrpc;
import availability.AvailabilityService;
import reservation.ReservationServiceOuterClass.CreateReservationRequest;
import reservation.ReservationServiceOuterClass.ReservationResponse;

@Service
public class AgencyService {


        @GrpcClient("availability-service")
        private HotelAvailabilityServiceGrpc.HotelAvailabilityServiceBlockingStub availabilityStub;

        @GrpcClient("reservation-service")
        private ReservationServiceGrpc.ReservationServiceBlockingStub reservationStub;




// Assuming reservationStub is the gRPC stub for ReservationService

    public ReservationResponse createReservation(CreateReservationRequest request) {
        try {
            return reservationStub.createReservation(request);
        } catch (io.grpc.StatusRuntimeException e) {
            throw new RuntimeException("Failed to create reservation: " + e.getStatus(), e);
        }
    }


    public AvailabilityService.CheckAvailabilityResponse checkAvailability(AvailabilityService.CheckAvailabilityRequest request) {
        try {
            return availabilityStub.checkAvailability(request);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check availability: " + e.getMessage(), e);
        }
    }
}