package com.example.server;

import com.example.server.mapper.TreePrinter;
import com.example.server.model.Amenity;
import com.example.server.model.Arrival;
import com.example.server.model.FareDetailsBySegment;
import com.example.server.model.Fee;
import com.example.server.model.Flight;
import com.example.server.model.Itinerary;
import com.example.server.model.Price;
import com.example.server.model.Segment;
import com.example.server.model.TravelerPricing;
import com.example.server.model.TravelerPricingPrice;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example.server")  // Ensure the base package is set correctly
@EnableScheduling
public class ServerApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        SpringApplication.run(ServerApplication.class, args);
        System.out.println("Server is running");
        TreePrinter.printClassDetailsForClass(Amenity.class);
        TreePrinter.printClassDetailsForClass(Arrival.class);
        TreePrinter.printClassDetailsForClass(FareDetailsBySegment.class);
        TreePrinter.printClassDetailsForClass(Fee.class);
        TreePrinter.printClassDetailsForClass(Flight.class);
        TreePrinter.printClassDetailsForClass(Itinerary.class);
        TreePrinter.printClassDetailsForClass(Price.class);
        TreePrinter.printClassDetailsForClass(Segment.class);
        TreePrinter.printClassDetailsForClass(TravelerPricing.class);
        TreePrinter.printClassDetailsForClass(TravelerPricingPrice.class);
    }

}
