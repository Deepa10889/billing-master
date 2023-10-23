package com.deepa.billing.controllers;

import com.deepa.billing.services.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/readings")
public class ElectricityReadingController {

    private final ReadingService readingService;

    @Autowired
    public ElectricityReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @PostMapping("/submit")
    public void submitElectricityReading(@RequestParam Long customerId, @RequestParam int reading) {
        LocalDate readingDate = LocalDate.now(); // Assuming readings are submitted for the current month
        readingService.submitElectricityReading(customerId, reading, readingDate);
    }
}