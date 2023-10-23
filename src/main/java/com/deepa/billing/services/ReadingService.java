package com.deepa.billing.services;

import com.deepa.billing.entities.ElectricityReading;
import com.deepa.billing.repositories.ElectricityReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReadingService {
    private final ElectricityReadingRepository readingRepository;

    @Autowired
    public ReadingService(ElectricityReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    public void submitElectricityReading(Long customerId, int reading, LocalDate readingDate) {
        ElectricityReading electricityReading = new ElectricityReading();
        electricityReading.setCustomerId(customerId);
        electricityReading.setCurrentReading(reading);
        electricityReading.setReadingDate(readingDate);
        readingRepository.save(electricityReading);
    }
}
