package com.example.grpc.service;


import com.example.grpc.model.Reservation;
import com.example.grpc.repository.HotelReservationService;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reservation.ReservationServiceGrpc;
import reservation.ReservationServiceOuterClass;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@GrpcService
public class ReservationServiceImpl extends ReservationServiceGrpc.ReservationServiceImplBase {

    @Autowired
    private HotelReservationService reservationRepository;

    @Override
    public void createReservation(ReservationServiceOuterClass.CreateReservationRequest request, StreamObserver<ReservationServiceOuterClass.ReservationResponse> responseObserver) {
        Timestamp startDate = request.getStartDate();
        Timestamp endDate = request.getEndDate();

// Convert Timestamp to sqlDate
        Instant startDateInstant = Instant.ofEpochSecond(startDate.getSeconds(), startDate.getNanos());
        LocalDateTime startDateLocal = LocalDateTime.ofInstant(startDateInstant, ZoneId.systemDefault());
        java.sql.Date startDateSql = java.sql.Date.valueOf(startDateLocal.toLocalDate()); // Convert to sqlDate

        Instant endDateInstant = Instant.ofEpochSecond(endDate.getSeconds(), endDate.getNanos());
        LocalDateTime endDateLocal = LocalDateTime.ofInstant(endDateInstant, ZoneId.systemDefault());
        java.sql.Date endDateSql = java.sql.Date.valueOf(endDateLocal.toLocalDate()); // Convert to sqlDate

        Reservation reservation = new Reservation();
        reservation.setName(request.getName());
        reservation.setStartDate(startDateSql);
        reservation.setEndDate(endDateSql);
        reservation.setOfferId(request.getOfferId());
        reservation.setAgencyId(request.getAgencyId());
        reservation.setReservationDate(new java.sql.Date(System.currentTimeMillis()));

        // Save the reservation
        Reservation savedReservation = saveReservation(reservation);

        // Convert the saved Reservation object to a ReservationResponse
        ReservationServiceOuterClass.ReservationResponse response = convertToReservationResponse(savedReservation);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void saveReservation(ReservationServiceOuterClass.ReservationResponse request, StreamObserver<ReservationServiceOuterClass.ReservationResponse> responseObserver) {
        // Convert the ReservationResponse to a Reservation object
        Reservation reservation = convertToReservation(request);

        // Save the reservation
        Reservation savedReservation = saveReservation(reservation);

        // Convert the saved Reservation object to a ReservationResponse
        ReservationServiceOuterClass.ReservationResponse response = convertToReservationResponse(savedReservation);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    // Method to save a reservation
    private Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Helper method to convert Reservation to ReservationResponse
    private ReservationServiceOuterClass.ReservationResponse convertToReservationResponse(Reservation reservation) {
        return ReservationServiceOuterClass.ReservationResponse.newBuilder()
                .setId(reservation.getId())
                .setName(reservation.getName())
                .setStartDate(Timestamp.newBuilder()
                        .setSeconds(reservation.getStartDate().getTime() / 1000)
                        .setNanos((int) (reservation.getStartDate().getTime() % 1000 * 1000000))
                        .build())
                .setEndDate(Timestamp.newBuilder()
                        .setSeconds(reservation.getEndDate().getTime() / 1000)
                        .setNanos((int) (reservation.getEndDate().getTime() % 1000 * 1000000))
                        .build())
                .setOfferId(reservation.getOfferId())
                .setAgencyId(reservation.getAgencyId())
                .setReservationDate(Timestamp.newBuilder()
                        .setSeconds(reservation.getReservationDate().getTime() / 1000)
                        .setNanos((int) (reservation.getReservationDate().getTime() % 1000 * 1000000))
                        .build())
                .build();
    }

    // Helper method to convert ReservationResponse to Reservation
    private Reservation convertToReservation(ReservationServiceOuterClass.ReservationResponse response) {
        Reservation reservation = new Reservation();
        reservation.setId(response.getId());
        reservation.setName(response.getName());
        reservation.setStartDate(Date.valueOf(response.getStartDate().toString()));
        reservation.setEndDate(Date.valueOf(response.getEndDate().toString()));
        reservation.setOfferId(response.getOfferId());
        reservation.setAgencyId(response.getAgencyId());
        reservation.setReservationDate(Date.valueOf(response.getReservationDate().toString()));
        return reservation;
    }
}