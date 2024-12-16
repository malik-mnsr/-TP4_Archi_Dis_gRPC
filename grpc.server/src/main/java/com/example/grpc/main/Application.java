package com.example.grpc.main;

import com.example.grpc.service.HotelAvailabilityServiceImpl;
import com.example.grpc.service.HotelServiceImpl;
import com.example.grpc.service.ReservationServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.grpc.protobuf.services.*;
@EntityScan(basePackages = {
		"com.example.grpc.model",
		"availability",
		"hotel"
})
@EnableJpaRepositories(basePackages = {
		"com.example.grpc.repository"
})

@SpringBootApplication(scanBasePackages ={
		"com.example.grpc.data",
		"com.example.grpc.service",


})

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	/*
	@Bean
	public CommandLineRunner startGrpcServer(HotelAvailabilityServiceImpl hotelAvailabilityService,
											 HotelServiceImpl hotelService,
											 ReservationServiceImpl reservationService,
											 @Value("${grpc.server.port:9095}") int grpcPort) {
		return args -> {
			// Create a gRPC server with reflection enabled
			Server server = ServerBuilder.forPort(grpcPort)

					.addService(hotelAvailabilityService)
					.addService(hotelService)
					.addService(reservationService)
					.addService(ProtoReflectionService.newInstance()) // Add reflection service
					.build();

			server.start();
			System.out.println("Server started on port " + grpcPort);
			server.awaitTermination();
		};
	}

	 */

}
