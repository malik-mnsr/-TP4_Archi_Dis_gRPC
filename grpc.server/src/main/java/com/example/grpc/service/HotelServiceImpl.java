package com.example.grpc.service;

import com.example.grpc.model.Hotel;
import com.example.grpc.repository.HotelRepository;
import hotel.HotelServiceGrpc;
import hotel.TestHotelServices;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class HotelServiceImpl extends HotelServiceGrpc.HotelServiceImplBase {

    @Autowired
    HotelRepository hotelRepository;
    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }
    @Override
    public void getAllHotels(TestHotelServices.Empty request, StreamObserver<TestHotelServices.HotelList> responseObserver) {
        if (hotelRepository == null) {
            throw new RuntimeException("hotelRepository is null");
        }
        else {
        List<Hotel> hotels = hotelRepository.findAll();
        List<TestHotelServices.HotelRpc> hotelRpcs = hotels.stream()
                .map(this::convertToHotelRpc)
                .collect(Collectors.toList());
            TestHotelServices.HotelList hotelList = TestHotelServices.HotelList.newBuilder().addAllHotels(hotelRpcs).build();
        responseObserver.onNext(hotelList);
        responseObserver.onCompleted();
    } }

    @Override
    public void getHotelById(TestHotelServices.HotelIdRequest request, StreamObserver<TestHotelServices.HotelRpc> responseObserver) {
        // Retrieve a hotel by its ID from your database or data source
        Hotel hotel = hotelRepository.findById(request.getId()).orElse(null);
        if (hotel!= null) {
            TestHotelServices.HotelRpc hotelRpc = convertToHotelRpc(hotel);
            responseObserver.onNext(hotelRpc);
        } else {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
        }
        responseObserver.onCompleted();
    }

    // Helper method to convert Hotel to HotelRpc
    private TestHotelServices.HotelRpc convertToHotelRpc(Hotel hotel) {
        return TestHotelServices.HotelRpc.newBuilder()
                .setId(hotel.getId())
                .setName(hotel.getName())
                .setRating(hotel.getStars())
                .setAddress(hotel.getAdr().toString())
                .build();
    }
}