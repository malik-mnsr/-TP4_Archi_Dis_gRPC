package com.example.grpc.service;

import availability.AvailabilityService;
import com.example.grpc.model.Offer;
import com.example.grpc.repository.HotelAvailabilityService;
import com.google.protobuf.Timestamp;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import availability.HotelAvailabilityServiceGrpc;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class HotelAvailabilityServiceImpl extends HotelAvailabilityServiceGrpc.HotelAvailabilityServiceImplBase {

    @Autowired
    private HotelAvailabilityService repository;

    @Override
    public void checkAvailability(AvailabilityService.CheckAvailabilityRequest request, StreamObserver<AvailabilityService.CheckAvailabilityResponse> responseObserver) {
        try {
            // Validate agency_id
            if (request.getAgenceId() != 1 && request.getAgenceId() != 2 && request.getAgenceId() != 3) {
                String errorMessage = "Invalid credentials provided for the agency.";
                logError(errorMessage);
                throw createStatusRuntimeException(Status.INVALID_ARGUMENT, errorMessage);
            }

            // Get offers for the given agency_id
            List<Offer> availableOffers = getOffersByAgency(request.getAgenceId());

            // Build response
            AvailabilityService.CheckAvailabilityResponse response = AvailabilityService.CheckAvailabilityResponse.newBuilder()
                    .addAllOffers(availableOffers.stream()
                            .map(this::convertToOfferRpc)
                            .collect(Collectors.toList()))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException());
        }
    }

    // Method to fetch offers based on agency_id
    private List<Offer> getOffersByAgency(int agencyId) {
        return repository.findAll().stream()
                .filter(offer -> offer.getAgencyId() == agencyId)
                .collect(Collectors.toList());
    }

    // Helper method to convert Offer to OfferRpc
    private AvailabilityService.OfferRpc convertToOfferRpc(Offer offer) {
        return AvailabilityService.OfferRpc.newBuilder()
                .setId(offer.getId())
                .setAgenceId(offer.getAgencyId())
                .setAvailabilityStart(Timestamp.newBuilder()
                        .setSeconds(offer.getAvailabilityStart().getTime() / 1000)
                        .setNanos((int) (offer.getAvailabilityStart().getTime() % 1000 * 1000000))
                        .build())
                .setAvailabilityEnd(Timestamp.newBuilder()
                        .setSeconds(offer.getAvailabilityEnd().getTime() / 1000)
                        .setNanos((int) (offer.getAvailabilityEnd().getTime() % 1000 * 1000000))
                        .build())
                .setNumberOfBeds(offer.getNumberOfBeds())
                .setHotelCity(offer.getChambre().getHotel().getAdr().getCity())

                .setHotelStars(offer.getChambre().getHotel().getStars())

                .build();
    }

    // Helper method to log errors
    private void logError(String message) {
        System.out.println("Error: " + message);
        // You can integrate with a logging framework here
    }

    // Helper method to create a StatusRuntimeException with detailed description
    private StatusRuntimeException createStatusRuntimeException(Status status, String description) {
        return status
                .withDescription(description)
                .augmentDescription("Please ensure the input matches the required format.")
                .asRuntimeException();
    }
}
