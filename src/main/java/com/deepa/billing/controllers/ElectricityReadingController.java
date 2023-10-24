package com.deepa.billing.controllers;

import com.deepa.billing.entities.ElectricityReading;
import com.deepa.billing.services.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class ElectricityReadingController {

    private final ReadingService readingService;

    @Autowired
    public ElectricityReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitElectricityReading(@RequestBody ElectricityReading electricityReading) {
        readingService.submitElectricityReading(electricityReading);
        return ResponseEntity.ok("Electricity reading submitted successfully.");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ElectricityReading>> getElectricityReadingsByCustomer(@PathVariable Long customerId) {
        List<ElectricityReading> electricityReadings = readingService.getElectricityReadingsByCustomer(customerId);
        return ResponseEntity.ok(electricityReadings);
    }
}