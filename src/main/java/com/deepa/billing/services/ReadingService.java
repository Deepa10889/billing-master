package com.deepa.billing.services;

import com.deepa.billing.entities.ElectricityReading;
import com.deepa.billing.repositories.ElectricityReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReadingService {
    private final ElectricityReadingRepository readingRepository;

    @Autowired
    public ReadingService(ElectricityReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    public void submitElectricityReading(ElectricityReading electricityReading) {
        if (electricityReading.getCurrentReading() < 0) {
            throw new IllegalArgumentException("Reading value cannot be negative.");
        }
        readingRepository.save(electricityReading);
    }

    public List<ElectricityReading> getElectricityReadingsByCustomer(Long customerId) {
        // Retrieve electricity readings by customer from the repository
        return readingRepository.findByCustomerId(customerId);
    }
}
