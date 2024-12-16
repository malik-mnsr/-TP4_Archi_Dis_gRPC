package com.example.grpc.client.cli;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import availability.AvailabilityService;
import reservation.ReservationServiceOuterClass;
import reservation.ReservationServiceOuterClass.CreateReservationRequest;
import com.example.grpc.client.service.AgencyService;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.example.grpc.client.cli.ClientCLI.ReservationService.generateReservationId;

@Component
public class ClientCLI implements CommandLineRunner {

    @Autowired
    private AgencyService agencyService; // Service to get hotels

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Availability CLI");

        // Authenticate the client using email and phone
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        String name = authenticateClient(email, phone);
        if (name == null) {
            System.out.println("Authentication failed: Client not found. Please check your email and phone number.");
            return;
        }

        System.out.println("Authentication successful. Welcome, " + name + "!");

        // Prompt the user for input fields
        System.out.print("Enter agency ID: ");
        int agencyId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter check-in date (yyyy-MM-dd): ");
        String checkInDateStr = scanner.nextLine();

        System.out.print("Enter check-out date (yyyy-MM-dd): ");
        String checkOutDateStr = scanner.nextLine();

        System.out.print("Enter minimum hotel stars: ");
        int minStars = Integer.parseInt(scanner.nextLine());

        // Define the date format and convert input to protobuf Timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInDate = dateFormat.parse(checkInDateStr);
        Date checkOutDate = dateFormat.parse(checkOutDateStr);
        Timestamp checkInTimestamp = Timestamps.fromMillis(checkInDate.getTime());
        Timestamp checkOutTimestamp = Timestamps.fromMillis(checkOutDate.getTime());

        // Create CheckAvailabilityRequest
        AvailabilityService.CheckAvailabilityRequest availabilityRequest = AvailabilityService.CheckAvailabilityRequest.newBuilder()
                .setAgenceId(agencyId)
                .setStartDate(checkInTimestamp)
                .setEndDate(checkOutTimestamp)
                .setMinStars(minStars)
                .setUsername(name)
                .build();

        // Call the agency service to check availability
        AvailabilityService.CheckAvailabilityResponse availabilityResponse;
        try {
            availabilityResponse = agencyService.checkAvailability(availabilityRequest);
        } catch (Exception e) {
            System.err.println("Error checking availability: " + e.getMessage());
            return;
        }

        // Display available offers
        System.out.println("Available Offers:");
        availabilityResponse.getOffersList().forEach(offer -> {
            Date availabilityStartDate = new Date(offer.getAvailabilityStart().getSeconds() * 1000);
            Date availabilityEndDate = new Date(offer.getAvailabilityEnd().getSeconds() * 1000);
            String formattedStartDate = dateFormat.format(availabilityStartDate);
            String formattedEndDate = dateFormat.format(availabilityEndDate);
            System.out.println("Offer ID: " + offer.getId());
            System.out.println("Availability: " + formattedStartDate + " to " + formattedEndDate);
            System.out.println("Stars: " + offer.getHotelStars());
            System.out.println("City: " + offer.getHotelCity());
            System.out.println();
        });

        System.out.print("Enter the Offer ID to make a reservation: ");
        int offerId = Integer.parseInt(scanner.nextLine());

        // Create reservation request
        CreateReservationRequest reservationRequest = CreateReservationRequest.newBuilder()
                .setAgencyId(agencyId)
                .setStartDate(checkInTimestamp)
                .setEndDate(checkOutTimestamp)
                .setOfferId(offerId)
                .setName(name)
                .build();

        // Call the reservation service
        try {
            ReservationServiceOuterClass.ReservationResponse response = agencyService.createReservation(reservationRequest);
            response.getName();
            response.getId();
            response.getAgencyId();
            response.getOfferId();
            response.getReservationDate();
            response.getStartDate();
            response.getEndDate();

            try {

                System.out.println(name + " vous voulez payé avec votre carte 1/0");
                int reply = scanner.nextInt();
                if (reply == 1) {
                    System.out.println("Reservation successful! Details:");
                    String reservationId = generateReservationId();
                    System.out.println("Reservation ID: " + reservationId);
                    double i = 120;
                    System.out.println("Total Price: " + i);
                } else {
                    System.out.println("Recommencer SVP");
                }
            } catch (Exception e) {
                System.err.println("Error making reservation: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String authenticateClient(String email, String phone) {
        String query = "SELECT name FROM client WHERE mail = ? AND phone = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, phone);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (Exception e) {
            System.err.println("Error during client authentication: " + e.getMessage());
        }
        return null;
    }

    public static class DatabaseConfig {
        public static Properties loadProperties() throws Exception {
            Properties properties = new Properties();
            try (InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
                if (inputStream == null) {
                    throw new Exception("database.properties file not found in resources.");
                }
                properties.load(inputStream);
            }
            return properties;
        }
    }

    public static class DatabaseConnector {
        private static Connection connection;

        public static Connection getConnection() throws SQLException {
            if (connection == null || connection.isClosed()) {
                try {
                    Properties properties = DatabaseConfig.loadProperties();
                    String url = properties.getProperty("db.url");
                    String username = properties.getProperty("db.username");
                    String password = properties.getProperty("db.password");
                    connection = DriverManager.getConnection(url, username, password);
                } catch (Exception e) {
                    throw new SQLException("Failed to establish database connection: " + e.getMessage(), e);
                }
            }
            return connection;
        }
    }

    public class ReservationService {

        // Method to generate a random Reservation ID
        public static String generateReservationId() {
            Random random = new Random();
            int randomNumber = random.nextInt(90000) + 10000; // Generates a number between 10000 and 99999
            return "RES" + randomNumber;
        }
    }
}
